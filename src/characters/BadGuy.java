package characters;

import stuff.Player;

// There are good and bad guys. Bad guys damage player.
public interface BadGuy {
  public void damage(Player player);
  public void setBlockingDoor(boolean isBlockingDoor);
  public boolean isBlockingDoor();
  public String getName();
}
