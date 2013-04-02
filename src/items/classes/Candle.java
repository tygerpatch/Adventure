package items.classes;

import items.interfaces.Unmoveable;

public class Candle extends Item implements Unmoveable {

  // *** Item
  @Override
  public String getName() {
    return "Candle";
  }

  @Override
  public String toString() {
    return getName();
  }
}
