package commands;

import items.interfacees.Defensive;
import items.interfacees.Drinkable;
import items.interfacees.Eatable;
import items.interfacees.Item;

import java.util.Scanner;

import stuff.Adventure;
import stuff.Player;
import stuff.Room;
import characters.BadGuy;
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
      if (item.getName().equalsIgnoreCase(strItem)) {
        boolean isUseableItem = false;

        if (item instanceof Drinkable) {
          Drinkable beverage = (Drinkable) item;
          player.drink(beverage);
          isUseableItem = true;
        }

        if (item instanceof Eatable) {
          Eatable food = (Eatable) item;
          player.eat(food);
          isUseableItem = true;
        }

        // I left the return statement out of the above two if statements in case in future items there is a Drinkable-Defensive-Item or an Eatable-Defensive-Item.

        if(item instanceof Defensive) {
          Room room = adventure.getCurrentRoom();

          if(room.hasOccupant()) {
            NonPlayableCharacter npc = room.getOccupant();

            if(npc instanceof BadGuy) {
              BadGuy badGuy = (BadGuy) npc;
              isUseableItem = true;

              if(((Defensive) item).useOn(badGuy)) {
                room.setOccupant(null);
              }

              return;
            }
          }

          System.out.println("There is nothing in the room to defend against.");
        }

        if(!isUseableItem) {
          // MoveableItems: Crown, Goblet, Jewel, Tome
          System.out.println("That item cannot be used here.");
        }

        return;
      }
    }

    System.out.println("You do not have that item in your knapsack.");
  }
}
