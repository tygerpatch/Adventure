package commands;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import stuff.Castle;
import stuff.DistanceComparator;
import stuff.Door;
import stuff.Path;
import stuff.Room;
import enums.Direction;

public class FleeCommand {

  public void check(Castle castle, Room fromRoom, Direction direction) {
    Door door = fromRoom.getDoor(direction);
    Room toRoom;

    if(door != null) {
      toRoom = castle.getRoom(door.open());

      if(toRoom.getDistance() > (fromRoom.getDistance() + 1)) {
        toRoom.setDistance(fromRoom.getDistance() + 1);
        toRoom.setPath(new Path(direction, fromRoom.getNumber()));
      }
    }
  }

  // Method that causes the user to flee to the starting room.
  // Will display each room that is visited to the console, but will not display the room's items or character.
  // You are required to use Dijkstra’s Shortest Path algorithm when you flee.
  // Your output should be the list of rooms, starting with the room you are in when you flee, and the directions taken through each room along your path.
  // You are running so fast, that the characters in each of the rooms along your path cannot hurt you.
  //@Override
  public void execute(Castle castle, Room currentRoom) {
    List<Room> list = new LinkedList<Room>();

    // Setup for Dijkstra's Algorithm
    // We start by saying we can't get to any of the rooms in the castle.
    for(Room room : castle) {
      room.setDistance(Integer.MAX_VALUE);
      room.setVisited(false);
      room.setPath(null);

      list.add(room);
    }

    // Next we look at all the rooms we can get to from the current room.
    Room toRoom, fromRoom;

    fromRoom = currentRoom;
    fromRoom.setDistance(0);

    check(castle, fromRoom, Direction.North);
    check(castle, fromRoom, Direction.East);
    check(castle, fromRoom, Direction.South);
    check(castle, fromRoom, Direction.West);

    // We'll also mark this path as complete so that we don't process it again.
    fromRoom.setVisited(true);
    list.remove(fromRoom);
    Comparator<Room> comparator = new DistanceComparator();
    Collections.sort(list, comparator);

    // All the remaining distances are going to be infinity because we have no paths to those yet.
    // We then repeat the above process, starting at first unvisited room with the shortest distance.
    while(!list.isEmpty()) {
      fromRoom = list.remove(0);

      check(castle, fromRoom, Direction.North);
      check(castle, fromRoom, Direction.East);
      check(castle, fromRoom, Direction.South);
      check(castle, fromRoom, Direction.West);

      // We'll also mark this path as complete so that we don't process it again.
      fromRoom.setVisited(true);
      Collections.sort(list, comparator);
    }

    // Starting from the Entrance Hall, work backwards until the current room is reached.
    toRoom = castle.findRoom("Entrance Hall");

    Stack<String> stack = new Stack<String>();
    StringBuilder strBuilder = new StringBuilder();
    Path path;

    while(toRoom != currentRoom) {
      path = toRoom.getPath();
      fromRoom = castle.getRoom(path.getRoomNumber());

      strBuilder.delete(0, strBuilder.length());
      strBuilder.append("From the ");
      strBuilder.append(fromRoom.getName());
      strBuilder.append(" you open the door on the ");
      strBuilder.append(path.getDirection().getName());
      strBuilder.append("-side and enter the ");
      strBuilder.append(toRoom.getName());
      strBuilder.append(".");

      stack.push(strBuilder.toString());

      toRoom = fromRoom;
    }

    // Display the paths to take to get from the current room to the Entrance Hall.
    while(!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    Castle castle = Castle.loadFrom("./dat-files/castle2.dat", scanner);
    scanner.close();
    Room room = castle.findRoom("Breakfast Room");
    FleeCommand command = new FleeCommand();
    command.execute(castle, room);
  }
}
