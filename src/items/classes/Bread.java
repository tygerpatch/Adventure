package items.classes;

import items.interfaces.Eatable;
import items.interfaces.Health;

public class Bread extends Item implements Eatable, Health {

  // *** Item
  @Override
  public String getName() {
    return "Bread";
  }

  @Override
  public String toString() {
    return getName();
  }
}
