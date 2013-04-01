package characters;

import stuff.Player;

// A harmless sort of bloke.
// Doesn’t hurt you, but he steals something from your bag.
// What gets stolen is random.  You do not get to chose.
// They’re just too dumb for spells to work on.
public class Hunchback implements NonPlayableCharacter {

  // *** NonPlayableCharacter interface
  @Override
  public void interactWith(Player player) {
    if(player.knapsack.isEmpty()) {
      System.out.println("The Hunchback just smiles at you as you walk out the door.");
    }
    else {
      int index = (int) (player.knapsack.size() * Math.random());
      System.out.println("The hunchback stole your " + player.knapsack.removeItem(index));
    }
  }

  @Override
  public String getName() {
    return "Hunchback";
  }
}
