package items.classes;

import items.interfaces.Unmoveable;

public class Nothing extends Item implements Unmoveable {

  // *** Item
  @Override
  public String getName() {
    return "Nothing";
  }

  @Override
  public String toString() {
    return getName();
  }
}
