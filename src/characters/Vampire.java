package characters;

import stuff.Player;

// A no-brainer bad guy.
// If you have garlic, you can pass without losing health.
// If you have a wooden stake, you can kill him.
// If you kill him, he is removed from the room.
// If you have no defense, your blood is sucked at the rate of 25 health points.
// Spells don’t work on vampires.
public class Vampire extends BadGuy implements NonPlayableCharacter {

  // *** BadGuy abstract class
  @Override
  public void damage(Player player) {
    System.out.println("The Vampire sucks your blood.");
    player.updateHealth(-25);
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
    return "Vampire";
  }
}
