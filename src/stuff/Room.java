package stuff;

import items.interfacees.Item;

import java.util.LinkedList;
import java.util.List;

import characters.NonPlayableCharacter;
import enums.Direction;

// Class to represent a room in the Adventure program
// In a graph structure, a room is a vertex
// Rooms will have doors, zero or one characters and zero or more items.
public class Room {

  private String name;
  private int[] doors = {0, 0, 0, 0};

  public void setName(String name) {
    this.name = name;
  }

  public void addDoor(Direction direction, int destination) {
    doors[direction.ordinal()] = destination;
  }

  public int[] getDoors() {
    return doors;
  }

  public String toString() {
    return name;
  }

  public List<Item> items =  new LinkedList<Item>();

  private NonPlayableCharacter occupant;

  public NonPlayableCharacter getOccupant() {
    return occupant;
  }

  public boolean hasDoor(Direction direction) {
    return (0 != doors[direction.ordinal()]);
  }

  public boolean hasOccupant() {
    return ((occupant != null) && (occupant.isAlive()));
  }

  private int number = 0;

  public int getNumber() {
    return number;
  }
}
