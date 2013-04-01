package characters;

import stuff.Player;

public interface NonPlayableCharacter {
  public void interactWith(Player player);
  public String getName();
}
