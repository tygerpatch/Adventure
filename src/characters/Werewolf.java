package characters;

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
  private boolean enchanted = false;

  @Override
  public void shot() {
    System.out.println("You killed the werewolf with the silver bullet.");
    alive = false;
  }

  @Override
  public void staked() {
    System.out.println("The werewolf wimpers as you stake it through the heart, but doesn't die.");
  }

  @Override
  public void enchanted() {
    System.out.println("You enchant the werewolf with a spell");
    enchanted = true;
    // TODO: pdf doesn't say when spells were off
  }

  @Override
  public boolean isAlive() {
    return alive;
  }
}
