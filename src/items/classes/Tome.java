package items.classes;

import items.interfacees.Item;

public class Tome implements Item {

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Tome";
  }
}
