package items.classes;

public class Jewel extends Item {

  // *** Item
  @Override
  public String getName() {
    return "Jewel";
  }

  @Override
  public String toString() {
    return getName();
  }
}
