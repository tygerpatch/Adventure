package items.classes;

import items.interfacees.Defensive;
import characters.BadGuy;
import characters.Vampire;

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
}
