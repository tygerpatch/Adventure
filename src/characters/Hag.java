package characters;

import stuff.Player;

// A Hag can be good or bad depending on your perspective.
// You must give her something out of your knapsack or she will take 10 points from your health.
// You should present the contents of the knapsack and let the user decide what to give her.
// Spells do not work on her.
public class Hag extends NonPlayableCharacter implements BadGuy {

  // *** BadGuy interface
  @Override
  public void damage(Player player) {
    System.out.println("You recieve damage from the Hag.");
    player.updateHealth(-10);
  }

  // *** NonPlayableCharacter abstract class
  @Override
  public void interactWith(Player player) {
    // TODO Auto-generated method stub
  }

  @Override
  public void clubbed() {
    // TODO Auto-generated method stub
  }

  @Override
  public void shot() {
    // TODO Auto-generated method stub
  }

  @Override
  public void staked() {
    // TODO Auto-generated method stub
  }

  @Override
  public void enchanted() {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean isAlive() {
    // TODO Auto-generated method stub
    return false;
  }
}

