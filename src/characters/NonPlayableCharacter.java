package characters;

import stuff.Player;

public abstract class NonPlayableCharacter {
  public abstract void interactWith(Player player);
  public abstract String getName();
}
