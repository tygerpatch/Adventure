package stuff;

import items.classes.Garlic;
import items.classes.Item;

import java.util.LinkedList;
import java.util.List;

import characters.BadGuy;
import characters.NonPlayableCharacter;
import characters.Wizard;
import enums.Direction;

// Class to represent a room in the Adventure program
// In a graph structure, a room is a vertex
// Rooms will have doors, zero or one characters and zero or more items.
public class Room {

  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private int number = 0;

  public int getNumber() {
    return number;
  }

  public void setNumber(int num) {
    this.number = num;
  }

  private Door[] doors = new Door[4];

  public void addDoor(Direction direction, int destination) {
    Door door = new Door();
    door.setDestination(destination);

    doors[direction.ordinal()] = door;
  }

  // Will return null if there is no door in the given direction
  public Door getDoor(Direction direction) {
    return doors[direction.ordinal()];
  }

  private List<Item> items =  new LinkedList<Item>();

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  public boolean hasItems() {
    return (items.size() > 0);
  }

  public List<Item> getItems() {
    return items;
  }

  private NonPlayableCharacter occupant;

  public NonPlayableCharacter getOccupant() {
    return occupant;
  }

  public void addOccupant(NonPlayableCharacter occupant) {
    this.occupant = occupant;
  }

  public boolean hasOccupant() {
    return (occupant != null);
  }

  public boolean hasBadGuy() {
    return (occupant instanceof BadGuy);
  }

  public BadGuy getBadGuy() {
    return (BadGuy) occupant;
  }

  // ** methods for Dijkstra's Algorithm
  private int distance;

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public int getDistance() {
    return distance;
  }

  private boolean visited;

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean wasVisited() {
    return visited;
  }

  private Path path;

  // Set the path I took to get to this Room.
  public void setPath(Path path) {
    this.path = path;
  }

  // Get the path I took to get to this Room.
  public Path getPath() {
    return path;
  }

  public static void main(String[] args) {
    Room room = new Room();
    System.out.println("-- Test methods on a Room that DOES NOT have an occupant or any Items --");
    System.out.println("Does the room have an occupant? " + (room.hasOccupant() ? "Yes" : "No"));
    System.out.println("Does the room have any items? " + (room.hasItems() ? "Yes" : "No"));

    System.out.println("-- Test that a Room DOES have an occupant and some items --");
    NonPlayableCharacter occupant = new Wizard();
    room.addOccupant(occupant);
    System.out.println("Does the room have an occupant? " + (room.hasOccupant() ? "Yes" : "No"));

    room.addItem(new Garlic());
    System.out.println("Does the room have any items? " + (room.hasItems() ? "Yes" : "No"));
  }
}
