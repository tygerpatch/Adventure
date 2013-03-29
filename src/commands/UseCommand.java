package commands;

import items.enums.DefensiveItem;
import items.interfacees.Drinkable;
import items.interfacees.Eatable;
import items.interfacees.Item;

import java.util.Scanner;

import stuff.Adventure;
import stuff.Player;
import stuff.Room;
import characters.NonPlayableCharacter;

public class UseCommand extends AbstractCommand {

  public UseCommand(Adventure adventure) {
    super(adventure);
  }

  // Method that allows the user to choose an item from the knapsack to use
  @Override
  public void execute() {
    Player player = adventure.getPlayer();

    if (player.knapsack.isEmpty()) {
      System.out.println("You do not have anything in your knapsack.");
      return;
    }

    System.out.print(player.knapsack + " What item would you like to use? ");

    Scanner scanner = adventure.getScanner();
    String strItem = scanner.nextLine();

    for (Item item : player.knapsack) {
      if (item.name().equalsIgnoreCase(strItem)) {
        if (item instanceof Drinkable) {
          player.drink((Drinkable) item);
        }
        else if (item instanceof Eatable) {
          player.eat((Eatable) item);
        }
        else if (item instanceof DefensiveItem) {
          Room currentRoom = adventure.getCurrentRoom();

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
}
