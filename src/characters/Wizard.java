package characters;

import items.enums.DefensiveItem;

import java.util.Scanner;

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
    if(player.hasGarlicBreath()) {
      System.out.println("Puzzled, the Wizard asks if you smell garlic.");
    }

    if(player.knapsack.size() < Knapsack.MAXIMUM_ALLOWABLE_ITEMS_IN_KNAPSACK) {
      System.out.println("The Wizard gives you a spell.");
      player.knapsack.addItem(DefensiveItem.Spell);
    }

    if(player.hasWounds()) {
      System.out.println("The Wizard was able to heal some of your wounds.");
      player.updateHealth(5);
    }
  }

  @Override
  public void clubbed() {
    System.out.println("The club suddenly turns into a palm leaf in your hand.");
    System.out.println("Embarassed, you put the leaf into your knapsack where it turns back into a club.");
  }

  @Override
  public void shot() {
    System.out.println("You try to shot the Wizard, but the bullet evaporates before it can touch him.");
  }

  @Override
  public void staked() {
    System.out.println("Before the stake can even touch the Wizard, he disappears in a puff of smoke.");
    System.out.println("Puzzled, you look around the room trying to spot him.");
    System.out.println("A few seconds later the Wizard reappears at the very same spot he disappeared from.");
  }

  @Override
  public void enchanted() {
    System.out.println("You try to enchant the wizard, but he casts a force field to block your spell.");
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  @Override
  public String toString() {
    return "Wizard";
  }

  @Override
  public void wakeUp() {
  }
}
