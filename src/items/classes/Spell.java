package items.classes;

import java.util.Scanner;

import items.interfaces.Defensive;
import characters.BadGuy;
import characters.Hag;
import characters.Troll;
import characters.Vampire;
import characters.Werewolf;

public class Spell extends Item implements Defensive {

  // *** Item
  @Override
  public String getName() {
    return "Spell";
  }

  @Override
  public String toString() {
    return getName();
  }

  // *** Defensive
  @Override
  public boolean useOn(BadGuy badGuy) {
    // You can get past Trolls and Werewolves with a Spell
    if((badGuy instanceof Troll) || (badGuy instanceof Werewolf)) {
      System.out.println("You enchant the " + badGuy.getName() + " with a spell.");
      badGuy.setBlockingDoor(false);
    }
    else {
      System.out.println("Spells don't work on " + badGuy.getName() + "s.");
    }

    return false;
  }

  public static void main(String[] args) {
    Defensive defensive = new Spell();

    System.out.println("-- Hag Test --");
    defensive.useOn(new Hag(new Scanner(System.in)));

    System.out.println("-- Troll Test --");
    defensive.useOn(new Troll());

    System.out.println("-- Vampire Test --");
    defensive.useOn(new Vampire());

    System.out.println("-- Werewolf Test --");
    defensive.useOn(new Werewolf());
  }
}
