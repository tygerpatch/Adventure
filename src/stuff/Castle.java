package stuff;

import items.classes.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import characters.NonPlayableCharacter;
import enums.Direction;
import exceptions.TooManyRoomsException;

public class Castle implements Iterable<Room> {

  // 99 indicating no more rooms
  public static final int NO_MORE_ROOMS = 99;

  // 99 indicating no more door information
  public static final int NO_MORE_PASSAGES = 99;

  // Rooms will be numbered consecutively, starting at 1, with a maximum of 98 rooms.
  List<Room> rooms = new LinkedList<Room>();

  public static final int MAXIMUM_NUMBER_OF_ROOMS = 98;

  public Room getRoom(int roomNumber) {
    for(Room room : rooms) {

      if(room.getNumber() == roomNumber) {
        return room;
      }
    }

    return new Room();
  }

  public void addRoom(Room room) {
    if((rooms.size() + 1) > MAXIMUM_NUMBER_OF_ROOMS) {
      throw new TooManyRoomsException(this);
    }

    rooms.add(room);
  }

  @Override
  public Iterator<Room> iterator() {
    return rooms.iterator();
  }

  public static Castle loadFrom(String filePath) throws FileNotFoundException {
    Castle castle = new Castle();

    Scanner scanner = new Scanner(new File(filePath));

    // Step 1: Read in all the rooms that are in the castle.
    // node and description: an integer followed by a space and up to 20 characters for the room name
    int node;
    Room room;

    while (NO_MORE_ROOMS != (node = scanner.nextInt())) {

      // create a new room with the given name and number
      room = new Room();

      room.setNumber(node);
      room.setName(scanner.nextLine().trim());

      castle.addRoom(room);
    }

    // Step 2: Read in all the passages in the castle
    // Indicated by triplets (room#, room#, direction): integer, integer, 1 character
    int origin, destination;
    Direction direction;

    while (NO_MORE_PASSAGES != (origin = scanner.nextInt())) {
      destination = scanner.nextInt();
      direction = Direction.fromChar(scanner.nextLine().trim().charAt(0));
      castle.getRoom(origin).addDoor(direction, destination);
    }

    // Step 3: Read in the details about each room.
    // Indicated by by room #, number of items in room, list of items in room
    int numItems;
    String str;
    Item item;

    // keep reading until end of stream is reached
    while (scanner.hasNextInt()) {
      room = castle.getRoom(scanner.nextInt());
      numItems = scanner.nextInt();

      for(int i = 0; i < numItems; i++) {
        str = scanner.next();

        if(null != (item = Item.fromString(str))) {
          room.addItem(item);
        }
        else {
          room.addOccupant(NonPlayableCharacter.fromString(str));
        }
      }
    }

    scanner.close();

    return castle;
  }

  public Room findRoom(String name) {
    for(Room room : this) {
      if(room.getName().equalsIgnoreCase(name)) {
        return room;
      }
    }

    return null;
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("-- Test maxing out number of rooms castle can have --");
    Castle castle = new Castle();
    for(int i = 1; i <= (Castle.MAXIMUM_NUMBER_OF_ROOMS + 1); i++) {
      try {
        castle.addRoom(new Room());
      }
      catch(TooManyRoomsException tooManyRooms) {
        tooManyRooms.printStackTrace();
      }
    }

    System.out.println("-- Test loading a castle from a file --");

    castle = Castle.loadFrom("./dat-files/castle.dat");

    Room room = castle.getRoom(3);
    System.out.println("-- Test that the " + room.getName() + " has an occupant and some items --");
    System.out.println("Does the Room have an occupant? " + (room.hasOccupant() ? "Yes" : "No"));
    System.out.println("Does the Room have any items? " + (room.hasItems() ? "Yes" : "No"));
  }
}
