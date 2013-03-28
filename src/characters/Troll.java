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

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    if(!clubbed && !enchanted) {
      damage(player);
    }
  }

  private boolean clubbed = false;
  private boolean enchanted = false;

  @Override
  public void clubbed() {
    System.out.println("You club the Troll unconscious.");
    clubbed = true;
  }

  @Override
  public void shot() {
    System.out.println("You shoot the troll.");
    System.out.println("Foul smelling blood oozes out of the wound, but the troll is still alive.");
  }

  @Override
  public void staked() {
    System.out.println("The Troll wimpers as you stake it through the heart, but doesn't die.");
  }

  @Override
  public void enchanted() {
    enchanted = false;
  }

  @Override
  public boolean isAlive() {
    return false;
  }
}
