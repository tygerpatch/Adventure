package commands;

import items.interfacees.Item;

import java.util.Scanner;

import stuff.Adventure;
import stuff.Player;
import stuff.Room;

public class RemoveCommand extends AbstractCommand {

  public RemoveCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    Player player = adventure.getPlayer();

    if (player.knapsack.isEmpty()) {
      System.out.println("You do not have anything in your knapsack.");
      return;
    }

    System.out.println("What item would you like to remove? " + player.knapsack);

    Scanner scanner = new Scanner(System.in);
    String strItem = scanner.next();
    scanner.close();

    Room currentRoom = adventure.getCurrentRoom();

    for (Item item : player.knapsack) {
      if (item.name().equalsIgnoreCase(strItem)) {
        currentRoom.items.add(item);
        player.knapsack.removeItem(item);
        return;
      }
    }

    System.out.println("You do not have that item in your knapsack.");
  }
}
