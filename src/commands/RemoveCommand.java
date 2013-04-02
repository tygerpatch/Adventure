package commands;

import items.classes.Bread;
import items.classes.Garlic;
import items.classes.Item;

import java.util.Scanner;

import stuff.Knapsack;
import stuff.Room;

public class RemoveCommand {

  public void execute(Knapsack knapsack, Scanner scanner, Room room) {
    if (knapsack.isEmpty()) {
      System.out.println("You do not have anything in your Knapsack.");
      return;
    }

    System.out.print(knapsack + " What item would you like to remove? ");
    String str = scanner.nextLine();

    for (Item item : knapsack) {
      if (item.getName().equalsIgnoreCase(str)) {
        room.addItem(item);
        knapsack.removeItem(item);
        return;
      }
    }

    System.out.println("You do not have that item in your Knapsack.");
  }

  public static void main(String[] args) {
    RemoveCommand command = new RemoveCommand();
    Knapsack knapsack = new Knapsack();
    Scanner scanner = new Scanner(System.in);
    Room room = new Room();

    System.out.println("-- Test trying to remove an Item from an empty Knapsack --");
    command.execute(knapsack, scanner, room);

    System.out.println("-- Add some Items to your Knapsack --");
    knapsack.addItem(new Garlic());
    knapsack.addItem(new Bread());

    System.out.println("-- Test trying to remove an Item from Knapsack --");
    command.execute(knapsack, scanner, room);
    System.out.println("Contents of Knapsack: " + knapsack);
    System.out.println("Items in Room: " + room.getItems());
  }
}
