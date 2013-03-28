package commands;

import stuff.Adventure;
import stuff.Room;
import enums.Direction;

public class DebugCommand extends AbstractCommand {

  public DebugCommand(Adventure adventure) {
    super(adventure);
  }

  // Debug – this is so that I can verify you built your graph correctly.
  // Output should be a list of rooms and connections, one per line.
  // example: kitchen north to living room
  @Override
  public void execute() {
    Room[] castle = adventure.getCastle();
    Room destination;
    Direction direction = Direction.North;

    // for each room in the castle
    for (Room origin : castle) {
      if (null != origin) {
        // for each door in the room
        for (int door : origin.getDoors()) {
          // if the door leads to another room
          if (null != (destination = castle[door])) {
            // display debugging information (ex. kitchen north to living room)
            System.out.println(origin + " " + direction + " to " + destination);
          }
          // ex. North -> East, East -> South
          direction = direction.nextClockwise();
        }
      }
    }
  }
}
