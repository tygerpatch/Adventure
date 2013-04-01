package items.classes;

import items.interfacees.Item;
import items.interfacees.Unmoveable;

public class Candle implements Unmoveable, Item {

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Candle";
  }
}
