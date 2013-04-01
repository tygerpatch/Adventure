package characters;

import stuff.Player;

// There are good and bad guys. Bad guys damage player.
public abstract class BadGuy {
  public abstract void damage(Player player);
  public abstract String getName();

  private boolean isBlockingDoor = true;

  public void setBlockingDoor(boolean isBlockingDoor) {
    this.isBlockingDoor = isBlockingDoor;
  }

  public boolean isBlockingDoor() {
    return isBlockingDoor;
  }
}
