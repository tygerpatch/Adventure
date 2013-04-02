package characters;

import items.classes.Item;

import java.util.Scanner;

import stuff.Player;

// A Hag can be good or bad depending on your perspective.
// You must give her something out of your knapsack or she will take 10 points from your health.
// You should present the contents of the knapsack and let the user decide what to give her.
// Spells do not work on her.
public class Hag extends BadGuy {

  private Scanner scanner;

  public Hag(Scanner scanner) {
    this.scanner = scanner;
  }

  // *** BadGuy
  @Override
  public void damage(Player player) {
    System.out.println("You recieve damage from the Hag.");
    player.updateHealth(-10);
  }

  // *** NonPlayableCharacter
  @Override
  public void interactWith(Player player) {
    if(player.knapsack.isEmpty()) {
      damage(player);
      return;
    }

    while(true) {
      System.out.println("The Hag demands that you give her an item.");
      System.out.print(player.knapsack + " What item would you like to give? ");

      String strItem = scanner.nextLine();

      for (Item item : player.knapsack) {
        if (item.getName().equalsIgnoreCase(strItem)) {
          player.knapsack.removeItem(item);
          return;
        }
      }

      System.out.println("You do not have that item in your knapsack.");
    }
  }

  @Override
  public String getName() {
    return "Hag";
  }
}
