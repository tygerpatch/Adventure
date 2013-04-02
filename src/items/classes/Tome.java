package items.classes;


public class Tome extends Item {

  // *** interface Item
  @Override
  public String getName() {
    return "Tome";
  }

  @Override
  public String toString() {
    return getName();
  }
}
