package characters;

import items.interfacees.Item;

import java.util.Scanner;

import stuff.Player;
import stuff.Room;

// A Hag can be good or bad depending on your perspective.
// You must give her something out of your knapsack or she will take 10 points from your health.
// You should present the contents of the knapsack and let the user decide what to give her.
// Spells do not work on her.
public class Hag extends NonPlayableCharacter implements BadGuy {

  private Scanner scanner;

  public Hag(Scanner scanner) {
    this.scanner = scanner;
  }

  // *** BadGuy interface
  @Override
  public void damage(Player player) {
    System.out.println("You recieve damage from the Hag.");
    player.updateHealth(-10);
  }

  // *** NonPlayableCharacter abstract class
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
        if (item.name().equalsIgnoreCase(strItem)) {
          player.knapsack.removeItem(item);
          return;
        }
      }

      System.out.println("You do not have that item in your knapsack.");
    }
  }

  @Override
  public void clubbed() {
    System.out.println("You suddenly have second thoughts about clubbing the Hag and decide not to do it.");
  }

  @Override
  public void shot() {
    System.out.println("Just as you pull the trigger the Hag steps out of the way, missing her by only a few inches.");
  }

  @Override
  public void staked() {
    System.out.println("You suddenly feel sorry for the Hag and drop the Wooden Stake to the floor.");
  }

  @Override
  public void enchanted() {
    System.out.println("The Hag cackles as your attempt to enchant her fails.");
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  @Override
  public String toString() {
    return "Hag";
  }

  @Override
  public void wakeUp() {
  }
}
