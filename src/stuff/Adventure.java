package stuff;

import items.enums.DefensiveItem;
import items.enums.MoveableItem;
import items.interfacees.Drinkable;
import items.interfacees.Eatable;
import items.interfacees.Item;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import util.ItemParser;

import characters.NonPlayableCharacter;
import enums.Direction;
// import static enums.Direction.*;

// Adventure is a text based action/adventure game.
//
// "Player navigates a complex castle, picking up tools and treasures,...while warding off evil beings."
//  (Adventure.doc, http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/index.htm , Dr. Liszka)
//
// The goal of the game is to find the King's crown and retrieve it for him.
//
// Note: Uses Entrance Hall as the starting room.
//
// @author Todd Gerspacher
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
    MENU.append("F, f\tFlee\n" + "Q, q\tQuit\n" + "D, d\tDebug");
  }

  public static void main(String[] args) throws IOException {
    Adventure game = new Adventure();

    game.loadCastle("./dat-files/castle.dat");
    game.play();
  }

  // Rooms will be numbered consecutively, starting at 1, with a maximum of 98 rooms.
  private static final int MAX_NUMBER_OF_ROOMS = (98 + 1);
  private Room[] castle = new Room[MAX_NUMBER_OF_ROOMS];

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

    // TODO: use linked list, then convert to array
    // TODO: store room.getNumber()
    while (NO_MORE_ROOMS != (node = scanner.nextInt())) {
      // create a new room with the given name and number
      room = new Room();
      description = scanner.nextLine().trim();
      room.setName(description);

      castle[node] = room;

      // The user starts in the Entrance Hallway.
      if ("Entrance Hall".equalsIgnoreCase(description)) {
        currentRoom = entranceHall = room;
      }
    }

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

    // keep reading until end of stream is reached
    while (scanner.hasNextInt()) {
      room = castle[scanner.nextInt()];
      numItems = scanner.nextInt();

      for(int i = 0; i < numItems; i++) {
        strItem = scanner.next();
        item = ItemParser.parseString(strItem);
        room.items.add(item);
      }
    }

    scanner.close();
  }

  // Debug – this is so that I can verify you built your graph correctly.
  // Output should be a list of rooms and connections, one per line.
  // example: kitchen north to living room
  public void debugCommand() {
    Room destination;
    Direction direction = Direction.North;

    // for each room in the castle
    for (Room origin : castle) {
      if (null != origin) {
        // for each door in the room
        for (int door : origin.getDoors()) {
          // if the door leads to another room
          if (null != (destination = castle[door])) {
            // display debugging information (ex. kitchen north to living room)
            System.out.println(origin + " " + direction + " to " + destination);
          }
          // ex. North -> East, East -> South
          direction = direction.nextClockwise();
        }
      }
    }
  }

  // Method that allows user to select an item to take from the room
  public void take() {
    // if there are no items in the current room
    if (currentRoom.items.isEmpty()) {
      System.out.println("There are no items to take from this room.");
      return;
    }

    // ex. What item would you like to take? bread, garlic, club
    System.out.print("What item would you like to take? " + currentRoom.items);

    // ex. <user types> club
    Scanner scanner = new Scanner(System.in);
    String strItem = scanner.next();
    scanner.close();

    for (Item item : currentRoom.items) {
      // if this is the item that the user selected
      if (item.name().equalsIgnoreCase(strItem)) {
        if (player.knapsack.addItem(item)) {
          currentRoom.items.remove(item);
          return;
        }
      }
    }

    System.out.println("Item not found in room.");
  }

  // Method that allows the user to choose an item from the knapsack to use
  private void useCommand() {
    if (player.knapsack.isEmpty()) {
      System.out.println("You do not have anything in your knapsack.");
      return;
    }

    System.out.println("What item would you like to use? " + player.knapsack);

    Scanner scanner = new Scanner(System.in);
    String strItem = scanner.next();
    scanner.close();

    for (Item item : player.knapsack) {
      if (item.name().equalsIgnoreCase(strItem)) {
        if (item instanceof Drinkable) {
          player.drink((Drinkable) item);
        }
        else if (item instanceof Eatable) {
          player.eat((Eatable) item);
        }
        else if (item instanceof DefensiveItem) {
          if(currentRoom.hasOccupant()) {
            NonPlayableCharacter occupant = currentRoom.getOccupant();

            if (item == DefensiveItem.Club) {
              // You can keep and reuse the club.
              occupant.clubbed();
            }
            else if (item == DefensiveItem.SilverBullet) {
              // The silver bullet may only be used once.
              player.knapsack.removeItem(item);
              occupant.shot();
            }
            else if (item == DefensiveItem.WoodenStake) {
              // The wooden stake may only be used once.
              player.knapsack.removeItem(item);
              occupant.staked();
            }
            else if (item == DefensiveItem.Spell) {
              // Once a spell is used, it is spent. It simply goes away.
              player.knapsack.removeItem(item);
              occupant.enchanted();
            }
          }
          else {
            System.out.println("There is nothing in the room to defend against.");
          }
        }
        else {
          // MoveableItems: Crown, Goblet, Jewel, Tome
          System.out.println("That item cannot be used here.");
        }

        return;
      }
    }

    System.out.println("You do not have that item in your knapsack.");
  }

  private Player player = new Player();

  public void enterPassage(Direction direction) {
    // if the current room has a door in the specified direction
    if(currentRoom.hasDoor(direction)) {

      // if the current room has an occupant
      if(currentRoom.hasOccupant()) {
        // then have the occupant interact with player before they enter the new room
        currentRoom.getOccupant().interactWith(player);
      }

      player.garlicBreath = false;

      // place user in new room
      int doors[] = currentRoom.getDoors();
      int newRoom = doors[direction.ordinal()];
      currentRoom = castle[newRoom];

      // Tell the user what room he/she is in
      System.out.println("You are now in the " + currentRoom);

      // Tell the user what and/or who is in the room with you.
      System.out.println("Items in the room: " + currentRoom.items);

      if(currentRoom.hasOccupant()) {
        System.out.println("The room also has an occupant: " + currentRoom.getOccupant());
      }

      // When you move to a new room, your health should be displayed.
      player.updateHealth(0);
    }
  }

  public void remove() {
    if (player.knapsack.isEmpty()) {
      System.out.println("You do not have anything in your knapsack.");
      return;
    }

    System.out.println("What item would you like to remove? " + player.knapsack);

    Scanner scanner = new Scanner(System.in);
    String strItem = scanner.next();
    scanner.close();

    for (Item item : player.knapsack) {
      if (item.name().equalsIgnoreCase(strItem)) {
        currentRoom.items.add(item);
        player.knapsack.removeItem(item);
        return;
      }
    }

    System.out.println("You do not have that item in your knapsack.");
  }

  private void processCommand(char ch) {
    // Command Pattern? takeCommand, moveCommand, debugCommand, useCommand

    // AbstractCommand command = commands.get(ch);
    // command.execute( );

    // North – move through the north door if there is one
    if(('N' == ch) || ('n' == ch)) {
      enterPassage(Direction.North);
    }
    //South – move through the south door if there is one
    else if(('S' == ch) || ('s' == ch)) {
      enterPassage(Direction.South);
    }
    //East – move through the east door if there is one
    else if(('E' == ch) || ('e' == ch)) {
      enterPassage(Direction.East);
    }
    //West – move through the west door if there is one
    else if(('W' == ch) || ('w' == ch)) {
      enterPassage(Direction.West);
    }
    //Contents – list the contents of the knapsack
    else if(('C' == ch) || ('c' == ch)) {
      if(player.knapsack.isEmpty()) {
        System.out.println("You do not have anything in your knapsack.");
      }
      else {
        System.out.println("In your knapsack you have: " + player.knapsack);
      }
    }
    //Use – use an item from the knapsack
    else if(('U' == ch) || ('U' == ch)) {
      useCommand();
    }
    //Take – pick up the item (same as Use)
    else if(('T' == ch) || ('t' == ch)) {
     take();
    }
    //Remove – remove an item from the knapsack (same as Use) but leave in room
    else if(('R' == ch) || ('r' == ch)) {
      remove();
    }
    //Debug – this is so that I can verify you built your graph correctly.
    //Output should be a list of rooms and connections, one per line.
    //example: kitchen north to living room
    else if(('D' == ch) || ('d' == ch)) {
      debugCommand();
    }
    // Flee – take the shortest path to the entry hallway to exit the castle
    else if(('F' == ch) || ('f' == ch)) {
      // TODO: flee to starting room
      flee();
    }
    else if(('Q' == ch) || ('q' == ch)) {
      stillPlaying = false;
    }
    else {
      System.out.println("I'm sorry. I don't understand that command.");
    }
  }

  private boolean stillPlaying;

  public void play() {
    System.out.println("King: Find me my crown!");

    // Tell the user what room he/she is in
    System.out.println("You are now in the " + currentRoom);

    // Tell the user what and/or who is in the room with you.
    System.out.println("Items in the room: " + currentRoom.items);

    if(currentRoom.hasOccupant()) {
      System.out.println("The room also has an occupant: " + currentRoom.getOccupant());
    }

    //Then use a loop that allows the user to interactively explore the castle
    // by entering one-letter directions (ex. E, e, W, w, N, n, S, s, F, f, D, d, Q, q).
    Scanner scanner = new Scanner(System.in);
    char command;
    stillPlaying = true;

    while (stillPlaying) {
      System.out.println(MENU.toString());
      System.out.println();
      System.out.println("Enter command: ");

      command = scanner.next().trim().charAt(0);
      processCommand(command);
    }
    scanner.close();

    for(Item item : player.knapsack) {
      if(item == MoveableItem.Crown) {
        System.out.println("King: Hurray! You found my crown!");
        return;
      }
    }

    System.out.println("King: Where's my crown!\n");
    System.out.println("Your head is chopped off by the King's executioner.  There's a lot of blood");
  }

  // Method that causes the user to flee to the starting room.
  // Will display each room that is visited to the console,
  // but will not display the room's items or character.
  private void flee() {
    // You are required to use Dijkstra’s Shortest Path algorithm when you flee.

    // Your output should be the list of rooms, starting with the room you are in when you flee,
    // and the directions taken through each room along your path.

    // You are running so fast, that the characters in each of the rooms along your path cannot hurt you.

    int[] distances = new int[castle.length];
    int[] fromRoom = new int[castle.length];
    boolean[] complete = new boolean[castle.length];

    for(int node = 1; node < castle.length; node++) {
      distances[node] = Integer.MAX_VALUE;
      fromRoom[node] = 0;
      complete[node] = false;
    }

    int roomNumber = currentRoom.getNumber();

    for(int door : currentRoom.getDoors()) {
      distances[door] = 1;
      fromRoom[door] = roomNumber;
    }
    complete[roomNumber] = true;

    int shortestDistance = Integer.MAX_VALUE;
    Room room = currentRoom;
    boolean done = false;

    while(!done) {
      done = true;

      for(int node = 1; node < castle.length; node++) {
        if((!complete[node]) && (distances[node] < shortestDistance)) {
          shortestDistance = distances[node];
          room = castle[node];
          roomNumber = room.getNumber();
          done = false;
        }
      }

      for(int door : room.getDoors()) {
        if(distances[door] > (distances[roomNumber] + 1)) {
          distances[door] = distances[roomNumber] + 1;
          fromRoom[door] = roomNumber;
        }
      }
      complete[roomNumber] = true;
    }

    List<String> output = new LinkedList<String>();
    room = entranceHall;
    int oldRoom;

    while(room != currentRoom) {
      roomNumber = room.getNumber();
      oldRoom = fromRoom[roomNumber];
      output.add("You flee from the " + castle[oldRoom] + " to the " + room);
      room = castle[oldRoom];
    }

    for(int i = output.size(); i > 0; i--) {
      System.out.println(output.get(i));
    }

    stillPlaying = false;
  }
}

// club
// it would be funny if the player could club the wizard?
// "The wizard was clubbed.  I hope your happy."

// Vampire – If you have garlic, you can pass without losing health.
// If you have a wooden stake, you can kill him.
// woodenstack
