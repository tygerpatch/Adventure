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

  private boolean isBlockingDoor = true;

  public void setBlockingDoor(boolean isBlockingDoor) {
    this.isBlockingDoor = isBlockingDoor;
  }

  public boolean isBlockingDoor() {
    return isBlockingDoor;
  }

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    if(isBlockingDoor) {
      damage(player);
    }
  }

  // *** Both BadGuy interface and NonPlayableCharacter abstract class
  @Override
  public String getName() {
    return "Werewolf";
  }
}
