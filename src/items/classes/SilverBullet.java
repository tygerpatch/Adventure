package items.classes;

import java.util.Scanner;

import items.interfaces.Defensive;
import characters.BadGuy;
import characters.Hag;
import characters.Troll;
import characters.Vampire;
import characters.Werewolf;

public class SilverBullet extends Item implements Defensive {

  // *** Item
  @Override
  public String getName() {
    return "Silver-Bullet";
  }

  @Override
  public String toString() {
    return getName();
  }

  // *** Defensive
  @Override
  public boolean useOn(BadGuy badGuy) {
    // Silver-Bullets kill Werewolves
    if(badGuy instanceof Werewolf) {
      System.out.println("You killed the Werewolf with the Silver-Bullet.");
      return true;
    }

    System.out.println("Silver-Bullets may only be used on Werewolves.");
    return false;
  }

  public static void main(String[] args) {
    Defensive defensive = new SilverBullet();

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
