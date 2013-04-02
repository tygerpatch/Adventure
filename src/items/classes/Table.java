package items.classes;

import items.interfaces.Unmoveable;

public class Table extends Item implements Unmoveable {

  // *** Item
  @Override
  public String getName() {
    return "Table";
  }

  @Override
  public String toString() {
    return getName();
  }
}
