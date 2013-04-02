package items.classes;

import java.util.Scanner;

import items.interfaces.Defensive;
import characters.BadGuy;
import characters.Hag;
import characters.Troll;
import characters.Vampire;
import characters.Werewolf;

public class Club extends Item implements Defensive {

  // *** Item
  @Override
  public String getName() {
    return "Club";
  }

  @Override
  public String toString() {
    return getName();
  }

  // *** Defensive
  @Override
  public boolean useOn(BadGuy badGuy) {
    // You can get past Trolls with a Club.
    if(badGuy instanceof Troll) {
      System.out.println("You club the Troll unconscious.");
      badGuy.setBlockingDoor(false);
    }
    else {
      System.out.println(badGuy.getName() + "s cannot be clubbed.");
    }

    return false;
  }

  public static void main(String[] args) {
    Defensive defensive = new Club();

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
