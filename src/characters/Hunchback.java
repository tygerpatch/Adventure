package characters;

import stuff.Knapsack;
import stuff.Player;

// A harmless sort of bloke.
// Doesn’t hurt you, but he steals something from your bag.
// What gets stolen is random.  You do not get to chose.
// They’re just too dumb for spells to work on.
public class Hunchback extends NonPlayableCharacter {

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    if(player.knapsack.isEmpty()) {
      System.out.println("The hunchback just smiles at you as you walk out the door.");
    }
    else {
      int index = (int) (player.knapsack.size() * Math.random());

      // The hunchback steals your (bread, spell, garlic, silver-bullet)
      System.out.println("The hunchback stole your " + player.knapsack.removeItem(index));
    }
  }

  @Override
  public void clubbed() {
    System.out.println("You bruise the Hunchback a little as you club him, but he is otherwise unharmed.");
  }

  @Override
  public void shot() {
    System.out.println("Just as you pull the trigger the Hunchback steps out of the way, missing him by only a few inches.");
  }

  @Override
  public void staked() {
    System.out.println("You suddenly feel sorry for the Hunchback and drop the wooden stake.");
  }

  @Override
  public void enchanted() {
    System.out.println("You try to enchant the Hunchback, but nothing seems to happen.");
  }

  @Override
  public boolean isAlive() {
    return false;
  }
}
