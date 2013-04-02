package commands.moving;

import stuff.Castle;
import stuff.Door;
import stuff.Player;
import stuff.Room;
import characters.BadGuy;
import characters.NonPlayableCharacter;
import enums.Direction;

public abstract class AbstractMovementCommand {

  protected void move(Player player, Direction direction, Castle castle) {
    Room room = player.getRoom();
    Door door = room.getDoor(direction);

    if(null == door) {
      System.out.println("The Room does not have a Door on the " + direction.getName() + "-side.");
      return;
    }

    if(room.hasOccupant()) {
      NonPlayableCharacter occupant = room.getOccupant();
      occupant.interactWith(player);
    }

    if(room.hasBadGuy()) {
      BadGuy badGuy = room.getBadGuy();
      badGuy.setBlockingDoor(true);
    }

    player.setRoom(castle.getRoom(door.open()));
  }
}
