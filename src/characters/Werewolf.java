package characters;

import stuff.Player;

// Again, not a good thing to run into.
// His bite is lethal.  You die.
// If you have a silver bullet, you can kill him (and remove him from the room).
// You can slip by him with a spell.
public class Werewolf extends BadGuy implements NonPlayableCharacter {

  // *** BadGuy abstract class
  @Override
  public void damage(Player player) {
    System.out.println("Oh no, the Werewolf has bitten you!");
    player.died();
  }

  // *** NonPlayableCharacter interface
  @Override
  public void interactWith(Player player) {
    if(isBlockingDoor()) {
      damage(player);
    }
  }

  // *** Both BadGuy abstract class and NonPlayableCharacter interface
  @Override
  public String getName() {
    return "Werewolf";
  }
}
