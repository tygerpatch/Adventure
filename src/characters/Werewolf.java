package characters;

import items.enums.DefensiveItem;

import java.util.Scanner;

import stuff.Player;

// Again, not a good thing to run into.
// His bite is lethal.  You die.
// If you have a silver bullet, you can kill him (and remove him from the room).
// You can slip by him with a spell.
public class Werewolf extends NonPlayableCharacter implements BadGuy {

  // *** BadGuy interface
  @Override
  public void damage(Player player) {
    System.out.println("Oh no, the Werewolf has bitten you!");
    player.died();
  }

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    if(alive) {
      if(enchanted) {
        System.out.println("You were able to slip past the werewolf with a spell.");
      }
      else {
        damage(player);
      }
    }
  }

  @Override
  public void clubbed() {
    System.out.println("You desperately try to club the werewolf to death, but nothing happens.");
    System.out.println("The werewolf just stares at you growling.");
  }

  private boolean alive = true;

  @Override
  public void shot() {
    System.out.println("You killed the Werewolf with the " + DefensiveItem.SilverBullet);
    alive = false;
  }

  @Override
  public void staked() {
    System.out.println("The Werewolf wimpers as you stake it through the heart, but doesn't die.");
  }

  @Override
  public void enchanted() {
    System.out.println("You enchant the werewolf with a spell");
    enchanted = true;
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public String toString() {
    return "Werewolf";
  }

  @Override
  public void wakeUp() {
  }
}
