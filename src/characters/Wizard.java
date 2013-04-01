package characters;

import items.classes.Spell;
import stuff.Knapsack;
import stuff.Player;

// This is a good guy.
// He will give you a spell.
// You do not need to defend yourself against him.
// He will also give you 5 points for health.
public class Wizard implements NonPlayableCharacter {

  // *** NonPlayableCharacter interface
  @Override
  public void interactWith(Player player) {
    if(player.knapsack.size() < Knapsack.MAXIMUM_ALLOWABLE_ITEMS_IN_KNAPSACK) {
      System.out.println("The Wizard gives you a spell.");
      player.knapsack.addItem(new Spell());
    }

    if(player.hasWounds()) {
      System.out.println("The Wizard was able to heal some of your wounds.");
      player.updateHealth(5);
    }
  }

  public String getName() {
    return "Wizard";
  }
}
