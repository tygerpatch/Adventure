package stuff;

import items.classes.Bread;
import items.classes.Elixir;
import items.classes.Garlic;
import items.classes.Item;
import items.interfaces.Drinkable;
import items.interfaces.Eatable;

public class Player {
  public Knapsack knapsack = new Knapsack();
  private int health = FULL_HEALTH;

  // ex. player.updateHealth(-10);
  public void updateHealth(int points) {
    health += points;

    if(health > FULL_HEALTH) {
      health = FULL_HEALTH;
    }

    if(health < 0) {
      health = 0;
    }

    System.out.println("Health: " + health);
  }

  // ex. player.drink(Elixir)
  public void drink(Drinkable drink) {
    // elixir (good for 25 points of health, only when you apply it)
    if(drink instanceof Elixir) {
      knapsack.removeItem((Item)drink);
      System.out.println("You drank the " + drink + ".");
      updateHealth(25);
    }
  }

  public void eat(Eatable food) {
    if(food instanceof Garlic) {
      // You can keep and reuse garlic
      System.out.println("You ate some Garlic.");
    }

    // bread (good for 10 points of health, only when you apply it)
    else if(food instanceof Bread) {
      knapsack.removeItem((Item)food);
      System.out.println("You ate the Bread.");
      updateHealth(10);
    }
  }

  public static final byte FULL_HEALTH = 100;

  public boolean hasWounds() {
    return (health < FULL_HEALTH);
  }

  public boolean isAlive() {
    return (health > 0);
  }

  public static void main(String[] args) {
    Player player = new Player();

    System.out.println("-- Test updating health past full --");
    player.updateHealth(10);

    System.out.println("-- Reduce Health, then test if player has wounds ---");
    player.updateHealth(-50);
    System.out.println("player.hasWounds() ? " + player.hasWounds());

    System.out.println("-- Test drinking Elixir --");
    Drinkable elixir = new Elixir();
    player.knapsack.addItem((Item)elixir);
    player.drink(elixir);

    System.out.println("-- Test eating Garlic --");
    Eatable garlic = new Garlic();
    player.knapsack.addItem((Item)garlic);
    player.eat(garlic);

    System.out.println("-- Test eating Bread --");
    Eatable bread = new Bread();
    player.knapsack.addItem((Item)bread);
    player.eat(bread);
  }
}
