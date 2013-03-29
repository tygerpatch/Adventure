package characters;

import java.util.Scanner;

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

  @Override
  public void clubbed() {
    System.out.println("You club the Troll unconscious.");
    clubbed = true;
  }

  @Override
  public void shot() {
    System.out.println("You shoot the troll.");
    System.out.println("Foul smelling blood oozes out of the wound, but the Troll is still alive.");
  }

  @Override
  public void staked() {
    System.out.println("The Troll goes 'Rrr..' and knocks the Wooden Stake out of your hands.");
  }

  @Override
  public void enchanted() {
    enchanted = true;
    System.out.println("You stunned the Troll with your Spell.");
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  @Override
  public String toString() {
    return "Troll";
  }

  @Override
  public void wakeUp() {
    if(clubbed) {
      clubbed = false;
      System.out.println("The troll slowly gets up from his nap.");
    }
  }
}
