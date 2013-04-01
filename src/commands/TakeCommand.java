package commands;

import items.interfacees.Item;

import java.util.Scanner;

import stuff.Adventure;
import stuff.Player;
import stuff.Room;

public class TakeCommand extends AbstractCommand {

  public TakeCommand(Adventure adventure) {
    super(adventure);
  }

  // Method that allows user to select an item to take from the room
  @Override
  public void execute() {
    Room currentRoom = adventure.getCurrentRoom();

    // if there are no items in the current room
    if (currentRoom.items.isEmpty()) {
      System.out.println("There are no items to take from this room.");
      return;
    }

    // ex. What item would you like to take? bread, garlic, club
    System.out.print(currentRoom.items + " What item would you like to take? ");

    // ex. <user types> club
    Scanner scanner = adventure.getScanner();
    String strItem = scanner.nextLine();

    Player player = adventure.getPlayer();

    for (Item item : currentRoom.items) {
      // if this is the item that the user selected
      if (item.getName().equalsIgnoreCase(strItem)) {
        if (player.knapsack.addItem(item)) {
          currentRoom.items.remove(item);
        }
        return;
      }
    }

    System.out.println("Item not found in room.");
  }
}
