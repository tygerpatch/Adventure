package characters;

import stuff.Player;

// There are good and bad guys. Bad guys damage player
public interface BadGuy {
  public void damage(Player player);
}
