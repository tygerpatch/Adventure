package items.classes;

import items.interfaces.Drinkable;
import items.interfaces.Health;

public class Elixir extends Item implements Drinkable, Health {

  // *** Item
  @Override
  public String getName() {
    return "Elixir";
  }

  @Override
  public String toString() {
    return getName();
  }
}
