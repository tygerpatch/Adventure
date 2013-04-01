package items.classes;

import items.interfacees.Eatable;
import items.interfacees.Health;
import items.interfacees.Item;

public class Bread implements Eatable, Health, Item {

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Bread";
  }
}
