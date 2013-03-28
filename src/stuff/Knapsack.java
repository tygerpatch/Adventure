package stuff;

import items.enums.UnmoveableItem;
import items.interfacees.Item;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// By implementing the Iterable interface, Knapsack can be used as the source in a foreach statement.
public class Knapsack implements Iterable<Item> {
  private List<Item> items = new LinkedList<Item>();

  public boolean isEmpty() {
    return items.isEmpty();
  }

  // You can only carry three items in your knapsack.
  public static final int MAXIMUM_ALLOWABLE_ITEMS_IN_KNAPSACK = 3;

  // ex. take(HealthItem.Bread) -> returns true if successfully taken
  public boolean addItem(Item item) {
    if(items.size() >= MAXIMUM_ALLOWABLE_ITEMS_IN_KNAPSACK) {
     System.out.println("Knapsack is full.  Please remove an item, before putting another in.");
     return false;
    }

    if(item instanceof UnmoveableItem) {
     System.out.println(item + " is an unmoveable item.");
     return false;
    }

    items.add(item);
    System.out.println("You added the " + item + " to your knapsack.");
    return true;
  }

  @Override
  public Iterator<Item> iterator() {
    return items.iterator();
  }

  @Override
  public String toString() {
    return items.toString();
  }

  public void removeItem(Item item) {
    if(items.remove(item)) {
      System.out.println("You removed the " + item + " from your knapsack.");
    }
  }

  public int size() {
    return items.size();
  }

  public Item removeItem(int index) {
    return items.remove(index);
  }
}
