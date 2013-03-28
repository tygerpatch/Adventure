package stuff;

import items.enums.DrinkableHealthItem;
import items.enums.EatableDefensiveItem;
import items.enums.EatableHealthItem;
import items.interfacees.Drinkable;
import items.interfacees.Eatable;

public class Player {
  public Knapsack knapsack = new Knapsack();
  private int health;

  // ex. player.updateHealth(-10);
  public void updateHealth(int points) {
    health += points;
    System.out.println("Health: " + health);
  }

  // ex. player.drink(Elixir)
  public void drink(Drinkable drink) {
    // elixir (good for 25 points of health, only when you apply it)
    if(drink == DrinkableHealthItem.Elixir) {
      knapsack.removeItem(drink);
      System.out.println("You drank the " + drink);
      updateHealth(25);
    }
  }

  public void eat(Eatable food) {
    if(food == EatableDefensiveItem.Garlic) {
      // You can keep and reuse garlic
      System.out.println("You ate some Garlic.");
    }
    // bread (good for 10 points of health, only when you apply it)
    else if(food == EatableHealthItem.Bread) {
      knapsack.removeItem(food);
      System.out.println("You ate the Bread.");
      updateHealth(10);
    }
  }

  public boolean garlicBreath = false;

  public void died() {
    System.out.println("You died.");
    health = 0;
    System.out.println("Health: " + health);
  }

  public static final byte FULL_HEALTH = 100;

  public boolean hasWounds() {
    return (health == FULL_HEALTH);
  }
}
