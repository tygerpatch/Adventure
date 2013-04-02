package stuff;

import items.classes.Crown;
import items.classes.Item;

import java.io.FileNotFoundException;
import java.util.Scanner;

import commands.DebugCommand;
import commands.FleeCommand;
import commands.RemoveCommand;
import commands.TakeCommand;
import commands.UseCommand;
import commands.moving.EastMovementCommand;
import commands.moving.NorthMovementCommand;
import commands.moving.SouthMovementCommand;
import commands.moving.WestMovementCommand;

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

  public static void main(String[] args) throws FileNotFoundException {

    Scanner scanner = new Scanner(System.in);
    Castle castle = Castle.loadFrom("./dat-files/castle.dat", scanner);
    System.out.println("King: Find me my crown!");
    Player player = new Player();
    player.setRoom(castle.findRoom("Entrance Hall"));
    char ch;

    UseCommand useCommand = new UseCommand();
    TakeCommand takeCommand = new TakeCommand();
    RemoveCommand removeCommand = new RemoveCommand();
    DebugCommand debugCommand = new DebugCommand();
    FleeCommand fleeCommand = new FleeCommand();

    NorthMovementCommand northCommand = new NorthMovementCommand();
    EastMovementCommand eastCommand = new EastMovementCommand();
    SouthMovementCommand southCommand = new SouthMovementCommand();
    WestMovementCommand westCommand = new WestMovementCommand();

    System.out.println(MENU.toString());
    System.out.println();

    boolean done = false;

    while (!done && player.isAlive()) {
      System.out.print("Enter command: ");

      ch = scanner.nextLine().trim().toUpperCase().charAt(0);

      // Use – use an item from the knapsack
      if('U' == ch) {
        useCommand.execute(player, scanner, player.getRoom());
      }
      // Take – pick up the item (same as Use)
      else if('T' == ch) {
        takeCommand.execute(player.getRoom(), player.knapsack, scanner);
      }
      // Remove – remove an item from the knapsack (same as Use) but leave in room
      else if('R' == ch) {
        removeCommand.execute(player.knapsack, scanner, player.getRoom());
      }
      // Debug – this is so that I can verify you built your graph correctly.
      // Output should be a list of rooms and connections, one per line.
      // example: kitchen north to living room
      else if('D' == ch) {
        debugCommand.execute(castle);
      }
      // Flee – take the shortest path to the entry hallway to exit the castle
      else if('F' == ch) {
        fleeCommand.execute(castle, player.getRoom());
        done = true;
      }
      // Quit the game
      else if('Q' == ch) {
        done = true;
      }
      // Contents – list the contents of the knapsack
      else if('C' == ch) {
        if(player.knapsack.isEmpty()) {
          System.out.println("You do not have anything in your knapsack.");
        }
        else {
          System.out.println("In your knapsack you have: " + player.knapsack);
        }
      }
      // North – move through the north door if there is one
      else if('N' == ch) {
        northCommand.execute(player, castle);
      }
      // East – move through the east door if there is one
      else if('E' == ch) {
        eastCommand.execute(player, castle);
      }
      // South – move through the south door if there is one
      else if('S' == ch) {
        southCommand.execute(player, castle);
      }
      else if('W' == ch) {
        westCommand.execute(player, castle);
      }
      else {
        System.out.println("That is not a valid command.");
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
}
