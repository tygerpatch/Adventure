package items.classes;

import items.interfacees.Defensive;
import items.interfacees.Item;
import characters.BadGuy;
import characters.Werewolf;

public class SilverBullet  implements Defensive, Item{

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Silver-Bullet";
  }

  // *** interface Defensive
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
}
