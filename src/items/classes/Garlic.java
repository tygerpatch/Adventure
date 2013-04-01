package items.classes;

import items.interfacees.Defensive;
import items.interfacees.Eatable;
import items.interfacees.Item;
import characters.BadGuy;
import characters.Vampire;

public class Garlic implements Eatable, Defensive, Item{

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Garlic";
  }

  // *** interface Defensive
  @Override
  public boolean useOn(BadGuy badGuy) {
    // You can get past Vampire with Garlic
    if(badGuy instanceof Vampire) {
      System.out.println("Smelling the Garlic on your breath, the Vampire gives you plenty of space in the room.");
      badGuy.setBlockingDoor(false);
    }

    return false;
  }
}
