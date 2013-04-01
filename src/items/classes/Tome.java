package items.classes;

import items.interfacees.Item;
import items.interfacees.Moveable;

public class Tome implements Moveable, Item {

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
