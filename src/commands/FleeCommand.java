package commands;

import java.util.LinkedList;
import java.util.List;

import stuff.Adventure;
import stuff.Room;

public class FleeCommand extends AbstractCommand {

  public FleeCommand(Adventure adventure) {
    super(adventure);
  }

  // Method that causes the user to flee to the starting room.
  // Will display each room that is visited to the console, but will not display the room's items or character.
  @Override
  public void execute() {
    // You are required to use Dijkstra’s Shortest Path algorithm when you flee.

    // Your output should be the list of rooms, starting with the room you are in when you flee,
    // and the directions taken through each room along your path.

    // You are running so fast, that the characters in each of the rooms along your path cannot hurt you.

    Room[] castle = adventure.getCastle();
    int[] distances = new int[castle.length];
    int[] fromRoom = new int[castle.length];
    boolean[] complete = new boolean[castle.length];

    for(int node = 1; node < castle.length; node++) {
      distances[node] = Integer.MAX_VALUE;
      fromRoom[node] = 0;
      complete[node] = false;
    }

    Room currentRoom = adventure.getCurrentRoom();
    int roomNumber = currentRoom.getNumber();

    for(int door : currentRoom.getDoors()) {
      distances[door] = 1;
      fromRoom[door] = roomNumber;
    }
    complete[roomNumber] = true;

    int shortestDistance = Integer.MAX_VALUE;
    Room room = currentRoom;
    boolean done = false;

    while(!done) {
      done = true;

      for(int node = 1; node < castle.length; node++) {
        if((!complete[node]) && (distances[node] < shortestDistance)) {
          shortestDistance = distances[node];
          room = castle[node];
          roomNumber = room.getNumber();
          done = false;
        }
      }

      for(int door : room.getDoors()) {
        if(distances[door] > (distances[roomNumber] + 1)) {
          distances[door] = distances[roomNumber] + 1;
          fromRoom[door] = roomNumber;
        }
      }
      complete[roomNumber] = true;
    }

    List<String> output = new LinkedList<String>();
    room = adventure.getEntranceHall();
    int oldRoom;

    while(room != currentRoom) {
      roomNumber = room.getNumber();
      oldRoom = fromRoom[roomNumber];
      output.add("You flee from the " + castle[oldRoom] + " to the " + room);
      room = castle[oldRoom];
    }

    for(int i = output.size(); i > 0; i--) {
      System.out.println(output.get(i));
    }

    adventure.setStillPlaying(false);
  }
}
