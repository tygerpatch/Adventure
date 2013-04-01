package util;

import items.classes.Bread;
import items.classes.Candle;
import items.classes.Club;
import items.classes.Crown;
import items.classes.Elixir;
import items.classes.Garlic;
import items.classes.Goblet;
import items.classes.Jewel;
import items.classes.Nothing;
import items.classes.SilverBullet;
import items.classes.Spell;
import items.classes.Table;
import items.classes.Tome;
import items.classes.WoodenStake;
import items.interfacees.Item;

import java.util.LinkedList;
import java.util.List;

public class ItemParser {
  private static final List<Item> items = new LinkedList<Item>();

  static {
    // Defensive Items
    items.add(new Club());
    items.add(new SilverBullet());
    items.add(new Spell());
    items.add(new WoodenStake());

    // Drinkable Health Items
    items.add(new Elixir());

    // Eatable Defensive Items
    items.add(new Garlic());

    // Eatable Health Item
    items.add(new Bread());

    // Moveable Items
    items.add(new Crown());
    items.add(new Goblet());
    items.add(new Jewel());
    items.add(new Tome());

    // Unmoveable Items
    items.add(new Candle());
    items.add(new Table());
    items.add(new Nothing());
  }

  public static Item parseString(String strItem) {
    for(Item item : items) {
      if(item.getName().equalsIgnoreCase(strItem)) {
        return item;
      }
    }

    return null;
  }
}
