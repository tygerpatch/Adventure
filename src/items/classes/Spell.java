package items.classes;

import items.interfacees.Defensive;
import items.interfacees.Item;
import characters.BadGuy;
import characters.Troll;
import characters.Werewolf;

public class Spell  implements Defensive, Item{

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Spell";
  }

  // *** interface Defensive
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
}
