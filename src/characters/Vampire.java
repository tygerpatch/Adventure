package characters;

import java.util.Scanner;

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

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    if(player.garlicBreath) {
      System.out.println("Smelling the garlic on your breath, the Vampire gives you plenty of room to slip past him.");
      return;
    }

    damage(player);
  }

  @Override
  public void clubbed() {
    System.out.println("You bruise the Vampire a little as you club him, but he is otherwise unharmed.");
  }

  @Override
  public void shot() {
    System.out.println("You shoot the vampire.");
    System.out.println("Foul smelling blood oozes out of the wound, but the vampire is still alive.");
  }

  @Override
  public void staked() {
    System.out.println("The Vampire goes 'Ack!' as you stab him through the heart.");
    System.out.println("The Vampire was killed.");
    alive = false;
  }

  private boolean alive = true;

  @Override
  public void enchanted() {
    System.out.println("You try to enchant the Vampire with the Spell, but it has no affect.");
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public String toString() {
    return "Vampire";
  }

  @Override
  public void wakeUp() {
  }
}
