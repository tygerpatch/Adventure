package items.classes;

import items.interfaces.Defensive;

import java.util.Scanner;

import characters.BadGuy;
import characters.Hag;
import characters.Troll;
import characters.Vampire;
import characters.Werewolf;

public class WoodenStake extends Item implements Defensive {

  // *** Item
  @Override
  public String getName() {
    return "Wooden-Stake";
  }

  @Override
  public String toString() {
    return getName();
  }

  // *** Defensive
  @Override
  public boolean useOn(BadGuy badGuy) {
    // Wooden-Stakes kill Vampires
    if(badGuy instanceof Vampire) {
      System.out.println("The Vampire goes 'Ack!' as you stab him through the heart, killing him instantly.");

      return true;
    }

    System.out.println("Wooden-Stakes may only be used on Vampires.");
    return false;
  }

  public static void main(String[] args) {
    Defensive defensive = new WoodenStake();

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
