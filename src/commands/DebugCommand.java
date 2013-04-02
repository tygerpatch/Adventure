package commands;

import java.io.FileNotFoundException;

import stuff.Castle;
import stuff.Door;
import stuff.Room;
import enums.Direction;

public class DebugCommand {

  // Debug – this is so that I can verify you built your graph correctly.
  // Output should be a list of rooms and connections, one per line.
  // example: kitchen north to living room
  public void execute(Castle castle) {
    for(Room room : castle) {
      debug(castle, room, Direction.North);
      debug(castle, room, Direction.East);
      debug(castle, room, Direction.South);
      debug(castle, room, Direction.West);
    }
  }

  private void debug(Castle castle, Room fromRoom, Direction direction) {
    Door door = fromRoom.getDoor(direction);

    if(door == null) {
      return;
    }

    // get the room the door opens into
    Room toRoom = castle.getRoom(door.open());

    // ex. Kitchen North to Living Room.
    System.out.print(fromRoom.getName());
    System.out.print(" ");
    System.out.print(direction.getName());
    System.out.print(" to ");
    System.out.print(toRoom.getName());
    System.out.println(".");
  }

  public static void main(String[] args) throws FileNotFoundException {
    new DebugCommand().execute(Castle.loadFrom("./dat-files/castle.dat"));
  }
}
