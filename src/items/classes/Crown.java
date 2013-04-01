package items.classes;

import items.interfacees.Item;

public class Crown implements Item {

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Crown";
  }
}
