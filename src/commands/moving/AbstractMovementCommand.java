package commands.moving;

import stuff.Adventure;
import stuff.Player;
import stuff.Room;
import characters.BadGuy;
import characters.NonPlayableCharacter;

import commands.AbstractCommand;

import enums.Direction;

public abstract class AbstractMovementCommand extends AbstractCommand {

  public AbstractMovementCommand(Adventure adventure) {
    super(adventure);
  }

  protected void enterPassage(Direction direction) {
    Room currentRoom = adventure.getCurrentRoom();

    // if the current room has a door in the specified direction
    if(currentRoom.hasDoor(direction)) {

      Player player = adventure.getPlayer();

      // if the current room has an occupant
      if(currentRoom.hasOccupant()) {
        // then have the occupant interact with player before they enter the new room
        NonPlayableCharacter npc = currentRoom.getOccupant();

        if(npc instanceof BadGuy) {
          BadGuy badGuy = (BadGuy) npc;

          if(badGuy.isBlockingDoor()) {
            npc.interactWith(player);
          }
          else {
            badGuy.setBlockingDoor(true);
          }
        }
        else {
          npc.interactWith(player);
        }

        if(player.isDead()) {
          adventure.setStillPlaying(false);
          return;
        }

      }

      Room[] castle = adventure.getCastle();

      // place user in new room
      int doors[] = currentRoom.getDoors();
      int newRoom = doors[direction.ordinal()];
      currentRoom = castle[newRoom];

      adventure.setCurrentRoom(currentRoom);

      // Tell the user what room he/she is in
      System.out.println("You are now in the " + currentRoom);

      // Tell the user what and/or who is in the room with you.
      if(!currentRoom.items.isEmpty()) {
        System.out.println("Items in the room: " + currentRoom.items);
      }

      if(currentRoom.hasOccupant()) {
        System.out.println("The room also has an occupant: " + currentRoom.getOccupant().getName());
      }

      // When you move to a new room, your health should be displayed.
      player.updateHealth(0);
    }
  }
}
