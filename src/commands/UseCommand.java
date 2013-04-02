package commands;

import items.classes.Bread;
import items.classes.Club;
import items.classes.Elixir;
import items.classes.Garlic;
import items.classes.Item;
import items.classes.WoodenStake;
import items.interfaces.Defensive;
import items.interfaces.Drinkable;
import items.interfaces.Eatable;

import java.util.Scanner;

import stuff.Knapsack;
import stuff.Player;
import stuff.Room;
import characters.BadGuy;
import characters.Troll;
import characters.Vampire;

public class UseCommand {

  public void execute(Player player, Drinkable beverage) {
    player.drink(beverage);
  }

  public void execute(Player player, Eatable food) {
    player.eat(food);
  }

  public void execute(Defensive item, BadGuy badGuy) {
    item.useOn(badGuy);
  }

  // Method that allows the user to choose an item from the knapsack to use
  public void execute(Player player, Scanner scanner, Room room) {
    Knapsack knapsack = player.knapsack;

    if (knapsack.isEmpty()) {
      System.out.println("You do not have anything in your Knapsack.");
      return;
    }

    System.out.print(knapsack + " What item would you like to use? ");
    String str = scanner.nextLine();

    Item item = null;

    for (Item xitem : knapsack) {
      if (xitem.getName().equalsIgnoreCase(str)) {
        item = xitem;
        break;
      }
    }

    if(null == item) {
      System.out.println("'" + str + "' is not in your Knapsack.");
      return;
    }

    if (item instanceof Drinkable) {
      execute(player, (Drinkable)item);
    }

    if (item instanceof Eatable) {
      execute(player, (Eatable)item);
    }

    // I left the return statement out of the above two if statements in case in future items there
    // is a Drinkable-Defensive-Item or an Eatable-Defensive-Item.
    if((item instanceof Defensive) && (room.hasBadGuy())) {
      BadGuy badGuy = room.getBadGuy();
      if(((Defensive)item).useOn(badGuy)) {
        room.addOccupant(null);
      }

      return;
    }

    System.out.println("That item cannot be used here.");
  }

  public static void main(String[] args) {
    Player player = new Player();
    Knapsack knapsack = player.knapsack;
    UseCommand command = new UseCommand();
    Scanner scanner = new Scanner(System.in);

    System.out.println("-- Create a Room with a BadGuy --");
    Room room = new Room();
    room.addOccupant(new Vampire());
    System.out.println("Does the Room have a BadGuy? " + (room.hasBadGuy() ? "Yes" : "No"));

    System.out.println("-- Test trying to use an Item from an empty Knapsack --");
    command.execute(player, scanner, room);

    System.out.println("-- Test trying to use a Drinkable Item --");
    Drinkable elixir = new Elixir();
    knapsack.addItem((Item) elixir);
    command.execute(player, elixir);

    System.out.println("-- Test trying to use an Eatable Item --");
    Eatable bread = new Bread();
    knapsack.addItem((Item)bread);
    command.execute(player, bread);

    System.out.println("-- Test trying to defend against a BadGuy --");
    command.execute(new Club(), new Troll());

    System.out.println("-- Refill Knapsack with one Drinkable, one Eatable, and one Defensive Item --");
    player = new Player();
    knapsack = player.knapsack;
    knapsack.addItem(new Elixir());
    knapsack.addItem(new Garlic());
    knapsack.addItem(new WoodenStake());

    System.out.println("-- Test allowing the user to choose which Item to use --");
    command.execute(player, scanner, room);
    System.out.println("Does the Room have a BadGuy? " + (room.hasBadGuy() ? "Yes" : "No"));
  }
}
