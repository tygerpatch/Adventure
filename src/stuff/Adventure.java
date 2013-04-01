package stuff;

import items.classes.Crown;
import items.interfacees.Item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import util.ItemParser;
import util.NonPlayableCharacterParser;
import characters.NonPlayableCharacter;

import commands.AbstractCommand;
import commands.ContentsCommand;
import commands.DebugCommand;
import commands.FleeCommand;
import commands.QuitCommand;
import commands.RemoveCommand;
import commands.TakeCommand;
import commands.UseCommand;
import commands.moving.EastMovementCommand;
import commands.moving.NorthMovementCommand;
import commands.moving.SouthMovementCommand;
import commands.moving.WestMovementCommand;

import enums.Direction;
// import static enums.Direction.*;

// Adventure is a text based action/adventure game.
// Player navigates a complex castle, picking up tools and treasures, while warding off evil beings.
// The goal of the game is to find the King's crown and retrieve it for him.
public class Adventure {

  private static final StringBuilder MENU = new StringBuilder();

  static {
    MENU.append("- Command List -\n");
    MENU.append("\n");
    MENU.append("N, n\tNorth - move through the north door if there is one\n");
    MENU.append("S, s\tSouth - move through the south door if there is one\n");
    MENU.append("E, e\tEast - move through the east door if there is one\n");
    MENU.append("W, w\tWest - move through the west door if there is one\n");
    MENU.append("\n");
    MENU.append("C, c\tContents - list the contents of the knapsack\n");
    MENU.append("U, u\tUse - use an item from the knapsack\n");
    MENU.append("T, t\tTake - pick up an item from the room\n");
    MENU.append("R, r\tRemove - remove an item from the knapsack\n");
    MENU.append("\n");
    MENU.append("F, f\tFlee\n");
    MENU.append("Q, q\tQuit\n");
    MENU.append("D, d\tDebug");
  }

  public static void main(String[] args) throws IOException {
    Adventure game = new Adventure();

    game.loadCastle("./dat-files/castle.dat");
    game.play();
  }

  // Rooms will be numbered consecutively, starting at 1, with a maximum of 98 rooms.
  private Room[] castle;

  // 99 indicating no more rooms
  public static final int NO_MORE_ROOMS = 99;

  // 99 indicating no more door information
  public static final int NO_MORE_PASSAGES = 99;

  private Room currentRoom, entranceHall;

  public void loadCastle(String filePath) throws IOException {
    Scanner scanner = new Scanner(new File(filePath));

    // Step 1: Read in all the rooms that are in the castle.
    // node and description: an integer followed by a space and up to 20 characters for the room name
    int node;
    String description;
    Room room;
    List<Room> rooms = new LinkedList<Room>();

    while (NO_MORE_ROOMS != (node = scanner.nextInt())) {
      // create a new room with the given name and number
      room = new Room();
      description = scanner.nextLine().trim();

      room.setName(description);
      room.setNumber(node);

      rooms.add(room);

      // The user starts in the Entrance Hallway.
      if ("Entrance Hall".equalsIgnoreCase(description)) {
        currentRoom = entranceHall = room;
      }
    }

    castle = (Room[]) rooms.toArray();

    // Step 2: Read in all the passages in the castle
    // Indicated by triplets (room#, room#, direction): integer, integer, 1 character
    int origin, destination;
    char ch;

    while (NO_MORE_PASSAGES != (origin = scanner.nextInt())) {
      destination = scanner.nextInt();

      ch = scanner.nextLine().trim().charAt(0);
      castle[origin].addDoor(Direction.parseChar(ch), destination);
    }

    // Step 3: Read in the details about each room.
    // Indicated by by room #, number of items in room, list of items in room
    int numItems;
    String strItem;
    Item item;
    NonPlayableCharacter npc;

    // keep reading until end of stream is reached
    while (scanner.hasNextInt()) {
      room = castle[scanner.nextInt()];
      numItems = scanner.nextInt();

      for(int i = 0; i < numItems; i++) {
        strItem = scanner.next();

        item = ItemParser.parseString(strItem);

        if(null != item) {
          room.items.add(item);
        }
        else {
          npc = NonPlayableCharacterParser.parseString(strItem, this.scanner);
          room.setOccupant(npc);
        }
      }
    }

    scanner.close();
  }

  private Player player = new Player();

  private boolean stillPlaying;
  private Scanner scanner;

  public void play() {
    System.out.println("King: Find me my crown!");

    // Tell the user what room he/she is in
    System.out.println("You are now in the " + currentRoom);

    // Tell the user what and/or who is in the room with you.
    System.out.println("Items in the room: " + currentRoom.items);

    if(currentRoom.hasOccupant()) {
      System.out.println("The room also has an occupant: " + currentRoom.getOccupant().getName());
    }

    //Then use a loop that allows the user to interactively explore the castle
    // by entering one-letter directions (ex. E, e, W, w, N, n, S, s, F, f, D, d, Q, q).
    char ch;
    stillPlaying = true;
    AbstractCommand command;

    System.out.println(MENU.toString());
    System.out.println();

    while (stillPlaying) {
      System.out.print("Enter command: ");

      ch = scanner.nextLine().trim().toUpperCase().charAt(0);
      command = mapCommands.get(Character.valueOf(ch));

      if(null == command) {
        System.out.println("I'm sorry. I don't understand that command.");
      }
      else {
        command.execute();
      }
    }

    scanner.close();

    if(player.isAlive()) {
      for(Item item : player.knapsack) {
        if(item instanceof Crown) {
          System.out.println("King: Hurray! You found my crown!");
          return;
        }
      }

      System.out.println("King: Where's my crown!");
      System.out.println("Your head is chopped off by the King's executioner.");
      System.out.println("There's a lot of blood.");
    }
  }

  public Room[] getCastle() {
    return castle;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public Room getEntranceHall() {
    return entranceHall;
  }

  public void setStillPlaying(boolean flag) {
    stillPlaying = flag;
  }

  public Player getPlayer() {
    return player;
  }

  private Map<Character, AbstractCommand> mapCommands = new HashMap<Character, AbstractCommand>();

  public Adventure() {
    scanner = new Scanner(System.in);

    // Use – use an item from the knapsack
    mapCommands.put(Character.valueOf('U'), new UseCommand(this));

    // Take – pick up the item (same as Use)
    mapCommands.put(Character.valueOf('T'), new TakeCommand(this));

    // Remove – remove an item from the knapsack (same as Use) but leave in room
    mapCommands.put(Character.valueOf('R'), new RemoveCommand(this));

    // Debug – this is so that I can verify you built your graph correctly.
    // Output should be a list of rooms and connections, one per line.
    // example: kitchen north to living room
    mapCommands.put(Character.valueOf('D'), new DebugCommand(this));

    // Flee – take the shortest path to the entry hallway to exit the castle
    mapCommands.put(Character.valueOf('F'), new FleeCommand(this));

    // Quit the game
    mapCommands.put(Character.valueOf('Q'), new QuitCommand(this));

    // Contents – list the contents of the knapsack
    mapCommands.put(Character.valueOf('C'), new ContentsCommand(this));

    // North – move through the north door if there is one
    mapCommands.put(Character.valueOf('N'), new NorthMovementCommand(this));

    // South – move through the south door if there is one
    mapCommands.put(Character.valueOf('S'), new SouthMovementCommand(this));

    // East – move through the east door if there is one
    mapCommands.put(Character.valueOf('E'), new EastMovementCommand(this));

    // West – move through the west door if there is one
    mapCommands.put(Character.valueOf('W'), new WestMovementCommand(this));
  }

  public void setCurrentRoom(Room room) {
    this.currentRoom = room;
  }

  public Scanner getScanner() {
    return this.scanner;
  }
}
