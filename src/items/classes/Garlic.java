package items.classes;

import items.interfacees.Defensive;
import items.interfacees.Eatable;
import characters.BadGuy;
import characters.Vampire;

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
}
