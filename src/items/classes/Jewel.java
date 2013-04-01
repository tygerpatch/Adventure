package items.classes;

import items.interfacees.Item;

public class Jewel implements Item {

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
