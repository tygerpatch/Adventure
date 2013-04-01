package characters;

import stuff.Player;

// This is definitely a bad guy.
// You can get past him unharmed with a club or a spell.
// Otherwise he takes 10 points from your health and bruises you pretty bad, too.
public class Troll extends NonPlayableCharacter implements BadGuy {

  // *** BadGuy interface
  @Override
  public void damage(Player player) {
    System.out.println("The Troll bruises you pretty badly as you leave the room.");
    player.updateHealth(-10);
  }

  private boolean isBlockingDoor = true;

  public void setBlockingDoor(boolean isBlockingDoor) {
    this.isBlockingDoor = isBlockingDoor;
  }

  @Override
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
    return "Troll";
  }
}
