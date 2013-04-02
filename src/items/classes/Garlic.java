package items.classes;

import java.util.Scanner;

import items.interfaces.Defensive;
import items.interfaces.Eatable;
import characters.BadGuy;
import characters.Hag;
import characters.Troll;
import characters.Vampire;
import characters.Werewolf;

public class Garlic extends Item implements Eatable, Defensive {

  // *** Item
  @Override
  public String getName() {
    return "Garlic";
  }

  @Override
  public String toString() {
    return getName();
  }

  // *** Defensive
  @Override
  public boolean useOn(BadGuy badGuy) {
    // You can get past Vampire with Garlic
    if(badGuy instanceof Vampire) {
      System.out.println("Smelling the Garlic on your breath, the Vampire gives you plenty of space in the room.");
      badGuy.setBlockingDoor(false);
    }

    return false;
  }

  public static void main(String[] args) {
    Defensive defensive = new Garlic();

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
