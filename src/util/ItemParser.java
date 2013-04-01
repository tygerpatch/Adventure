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

public class ItemParser {

  public static Item parseString(String strItem) {
    // *** Defensive Items
    if("Club".equalsIgnoreCase(strItem)) {
      return new Club();
    }

    if("Silver-Bullet".equalsIgnoreCase(strItem)) {
      return new SilverBullet();
    }

    if("Spell".equalsIgnoreCase(strItem)) {
      return new Spell();
    }

    if("Wooden-Stake".equalsIgnoreCase(strItem)) {
      return new WoodenStake();
    }

    // *** Drinkable Health Items
    if("Elixir".equalsIgnoreCase(strItem)) {
      return new Elixir();
    }

    // *** Eatable Defensive Items
    if("Garlic".equalsIgnoreCase(strItem)) {
      return new Garlic();
    }

    // *** Eatable Health Item
    if("Bread".equalsIgnoreCase(strItem)) {
      return new Bread();
    }

    // *** Unmoveable Items
    if("Candle".equalsIgnoreCase(strItem)) {
      return new Candle();
    }

    if("Table".equalsIgnoreCase(strItem)) {
      return new Table();
    }

    if("Nothing".equalsIgnoreCase(strItem)) {
      return new Nothing();
    }

    // *** Items
    if("Crown".equalsIgnoreCase(strItem)) {
      return new Crown();
    }

    if("Goblet".equalsIgnoreCase(strItem)) {
      return new Goblet();
    }

    if("Jewel".equalsIgnoreCase(strItem)) {
      return new Jewel();
    }

    if("Tome".equalsIgnoreCase(strItem)) {
      return new Tome();
    }

    return null;
  }
}
