package characters;

import stuff.Player;

public abstract class NonPlayableCharacter {
  public abstract void interactWith(Player player);
  public abstract void clubbed();
  public abstract void shot();
  public abstract void staked();
  public abstract void enchanted();
  public abstract boolean isAlive();
}
