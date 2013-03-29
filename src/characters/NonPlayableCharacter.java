package characters;

import java.util.Scanner;

import stuff.Player;

public abstract class NonPlayableCharacter {
  protected boolean enchanted = false;

  public abstract void interactWith(Player player);
  public abstract void clubbed();
  public abstract void shot();
  public abstract void staked();
  public abstract void enchanted();

  public void unenchant() {
    enchanted = false;
  }

  public abstract boolean isAlive();
  public abstract String toString();
  public abstract void wakeUp();
}
