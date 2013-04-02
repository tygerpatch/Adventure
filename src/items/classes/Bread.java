package items.classes;

import items.interfacees.Eatable;
import items.interfacees.Health;

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
