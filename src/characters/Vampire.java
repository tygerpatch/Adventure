package characters;

import stuff.Player;

// A no-brainer bad guy.
// If you have garlic, you can pass without losing health.
// If you have a wooden stake, you can kill him.
// If you kill him, he is removed from the room.
// If you have no defense, your blood is sucked at the rate of 25 health points.
// Spells don’t work on vampires.
public class Vampire extends NonPlayableCharacter implements BadGuy {

  // *** BadGuy interface
  @Override
  public void damage(Player player) {
    System.out.println("The Vampire sucks your blood.");
    player.updateHealth(-25);
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
    return "Vampire";
  }
}
