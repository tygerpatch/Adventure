package items.classes;

public abstract class Item {
  public abstract String getName();

  public static Item fromString(String strItem) {
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
