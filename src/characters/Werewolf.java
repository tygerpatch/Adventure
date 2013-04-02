package characters;

import stuff.Player;

// Again, not a good thing to run into.
// His bite is lethal.  You die.
// If you have a silver bullet, you can kill him (and remove him from the room).
// You can slip by him with a spell.
public class Werewolf extends BadGuy {

  // *** BadGuy
  @Override
  public void damage(Player player) {
    System.out.println("Oh no, the Werewolf has bitten you!");
    player.updateHealth(-Player.FULL_HEALTH);
  }

  // *** NonPlayableCharacter
  @Override
  public void interactWith(Player player) {
    if(isBlockingDoor()) {
      damage(player);
    }
  }

  @Override
  public String getName() {
    return "Werewolf";
  }

  public static void main(String[] args) {
    new Werewolf().interactWith(new Player());
  }
}
