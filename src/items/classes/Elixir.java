package items.classes;

import items.interfacees.Drinkable;
import items.interfacees.Health;
import items.interfacees.Item;

public class Elixir implements Drinkable, Health, Item {

  @Override
  public String toString() {
    return getName();
  }

  // *** interface Item
  @Override
  public String getName() {
    return "Elixir";
  }
}
