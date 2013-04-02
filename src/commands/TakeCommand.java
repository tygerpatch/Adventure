package commands;

import items.classes.Bread;
import items.classes.Crown;
import items.classes.Elixir;
import items.classes.Garlic;
import items.classes.Goblet;
import items.classes.Item;

import java.util.List;
import java.util.Scanner;

import stuff.Knapsack;
import stuff.Room;

public class TakeCommand {

  // Method that allows user to select an item to take from the room
//  @Override
  public void execute(Room room, Knapsack knapsack, Scanner scanner) {
    if (!room.hasItems()) {
      System.out.println("There are no items to take from this room.");
      return;
    }

    if(knapsack.isFull()) {
      System.out.println("You can not take any more items, because your Knapsack is full.");
      return;
    }

    // ex. What item would you like to take? bread, garlic, club
    List<Item> items = room.getItems();
    System.out.print(items + " What item would you like to take? ");

    // ex. <user types> club
    String str = scanner.nextLine();

    for (Item item : items) {
      if (item.getName().equalsIgnoreCase(str)) {
        knapsack.addItem(item);
        room.removeItem(item);
        return;
      }
    }

    System.out.println("The Room does not have a '" + str + "'.");
  }

  public static void main(String[] args) {
    TakeCommand command = new TakeCommand();
    Room room = new Room();
    Knapsack knapsack = new Knapsack();
    Scanner scanner = new Scanner(System.in);

    System.out.println("-- Test trying to take an Item from a Room that HAS NO items --");
    command.execute(room, knapsack, scanner);

    System.out.println("-- Add some items to room --");
    room.addItem(new Garlic());
    room.addItem(new Bread());
    room.addItem(new Crown());

    System.out.println("-- Test trying to take an Item from a Room that HAS items --");
    command.execute(room, knapsack, scanner);

    System.out.println("-- Add some items to knapsack --");
    knapsack.addItem(new Elixir());
    knapsack.addItem(new Goblet());
    System.out.println("Is knapsack full? " + (knapsack.isFull() ? "Yes" : "No"));

    System.out.println("-- Test trying to take an Item from a Room when Knapsack is full --");
    command.execute(room, knapsack, scanner);
  }
}
