package util;

import static items.enums.DefensiveItem.Club;
import static items.enums.DefensiveItem.SilverBullet;
import static items.enums.DefensiveItem.Spell;
import static items.enums.DefensiveItem.WoodenStake;
import static items.enums.DrinkableHealthItem.Elixir;
import static items.enums.EatableDefensiveItem.Garlic;
import static items.enums.EatableHealthItem.Bread;
import static items.enums.MoveableItem.Crown;
import static items.enums.MoveableItem.Goblet;
import static items.enums.MoveableItem.Jewel;
import static items.enums.MoveableItem.Tome;
import static items.enums.UnmoveableItem.Candle;
import static items.enums.UnmoveableItem.Nothing;
import static items.enums.UnmoveableItem.Table;
import items.interfacees.Item;

import java.util.LinkedList;
import java.util.List;

public class ItemParser {
  private static final List<Item> items = new LinkedList<Item>();

  static {
    // Defensive Items
    items.add(Club);
    items.add(SilverBullet);
    items.add(Spell);
    items.add(WoodenStake);

    // Drinkable Health Items
    items.add(Elixir);

    // Eatable Defensive Items
    items.add(Garlic);

    // Eatable Health Item
    items.add(Bread);

    // Moveable Items
    items.add(Crown);
    items.add(Goblet);
    items.add(Jewel);
    items.add(Tome);

    // Unmoveable Items
    items.add(Candle);
    items.add(Table);
    items.add(Nothing);
  }

  public static Item parseString(String strItem) {
    for(Item item : items) {
      if(item.name().equalsIgnoreCase(strItem)) {
        return item;
      }
    }

    return null;
  }
}
