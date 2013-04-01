package items.classes;

import items.interfacees.Item;
import items.interfacees.Moveable;

public class Jewel implements Moveable, Item {

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Jewel";
  }
}
