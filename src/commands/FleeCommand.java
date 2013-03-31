package commands;

import java.util.Stack;

import stuff.Adventure;
import stuff.Room;
import enums.Direction;

public class FleeCommand extends AbstractCommand {

  public FleeCommand(Adventure adventure) {
    super(adventure);
  }

  private class Node {
    public int distance = Integer.MAX_VALUE;
    public int fromRoom = 0;
    public boolean visited = false;
    public Direction direction = null;
  }

  // Method that causes the user to flee to the starting room.
  // Will display each room that is visited to the console, but will not display the room's items or character.
  // You are required to use Dijkstra’s Shortest Path algorithm when you flee.
  // Your output should be the list of rooms, starting with the room you are in when you flee, and the directions taken through each room along your path.
  // You are running so fast, that the characters in each of the rooms along your path cannot hurt you.
  @Override
  public void execute() {
    Room[] castle = adventure.getCastle();
    Node[] nodes = new Node[adventure.castleSize];

    // Start by initializing all the distances to infinity.
    for(int indx = 0; indx < nodes.length; indx++) {
      nodes[indx] = new Node();
    }

    // Next we look at all the rooms we can get to from the current room.
    Node node;
    Room room = adventure.getCurrentRoom();
    int[] doors = room.getDoors();

    for(int indx = 0; indx < 4; indx++) {
      node = nodes[doors[indx]];

      node.distance = 1;
      node.fromRoom = room.getNumber();
      node.direction = Direction.fromInteger(indx);
    }

    // We'll also mark this path as complete so that we don't process it again.
    nodes[room.getNumber()].visited = true;

    // All the remaining distances are going to be infinity because we have no paths to those yet.
    // We then repeat the above process, starting at the vertex with the shortest distance.
    int shortestDistance = Integer.MAX_VALUE;
    boolean done = false;

    while(!done) {
      done = true;  // indicates we have no more rooms to visit

      // Find the "closest" room we haven't visited yet.
      for(int indx = 1; indx < nodes.length; indx++) {
        if((!nodes[indx].visited) &&  (nodes[indx].distance < shortestDistance)) {
          shortestDistance = nodes[indx].distance;
          room = castle[indx];
          done = false; // indicates we have another room to visit
        }
      }

      // Update the information we have on each room this closest-room leads to.
      doors = room.getDoors();
      for(int indx = 0; indx < 4; indx++) {
        node = nodes[doors[indx]];

        if(node.distance > (nodes[room.getNumber()].distance + 1)) {
          node.distance = nodes[room.getNumber()].distance + 1;;
          node.fromRoom = room.getNumber();
          node.direction = Direction.fromInteger(indx);
        }
      }
      nodes[room.getNumber()].visited = true;
      shortestDistance = Integer.MAX_VALUE;
    }

    // Starting from the Entrance Hall, work backwards until the current room is reached.
    Room origin;
    Room currentRoom = adventure.getCurrentRoom();
    Room destination = adventure.getEntranceHall();
    Direction direction;
    StringBuilder strBuilder = new StringBuilder();
    Stack<String> stack = new Stack<String>();

    while(destination != currentRoom) {
      node = nodes[destination.getNumber()];
      origin = castle[node.fromRoom];
      direction = node.direction;

      strBuilder.delete(0, strBuilder.length());
      strBuilder.append("From the ");
      strBuilder.append(origin.getName());
      strBuilder.append(" you open the door on the ");
      strBuilder.append(direction.getName());
      strBuilder.append("-side and enter the ");
      strBuilder.append(destination.getName());
      strBuilder.append(".");

      stack.push(strBuilder.toString());

      destination = origin;
    }

    // Display the paths to take to get from the current room to the Entrance Hall.
    while(!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
    adventure.setStillPlaying(false);
  }
}
