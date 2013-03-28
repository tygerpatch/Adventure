package characters;

import items.enums.DefensiveItem;
import stuff.Knapsack;
import stuff.Player;

// This is a good guy.
// He will give you a spell.
// You do not need to defend yourself against him.
// He will also give you 5 points for health.
public class Wizard extends NonPlayableCharacter {

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    if(player.knapsack.size() < Knapsack.MAXIMUM_ALLOWABLE_ITEMS_IN_KNAPSACK) {
      System.out.println("The wizard gives you a spell.");
      player.knapsack.addItem(DefensiveItem.Spell);
    }

    if(player.hasWounds()) {
      System.out.println("The wizard was able to heal some of your wounds.");
      player.updateHealth(5);
    }
  }

  @Override
  public void clubbed() {
    System.out.println("You try to club the wizard, but a force field prevents you from harming him.");
  }

  @Override
  public void shot() {
    System.out.println("You try to shot the wizard, but the bullet evaporates before it can touch him.");
  }

  @Override
  public void staked() {
    System.out.println("You try to stake the wizard's heart, but the stake evaporates before it can touch him.");
  }

  @Override
  public void enchanted() {
    System.out.println("You try to enchant the wizard, but he blocked it.");
  }

  @Override
  public boolean isAlive() {
    return false;
  }
}
