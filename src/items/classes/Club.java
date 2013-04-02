package items.classes;

import items.interfacees.Defensive;
import characters.BadGuy;
import characters.Troll;

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
}
