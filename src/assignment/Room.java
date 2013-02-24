package assignment;
import java.lang.IllegalArgumentException;

/**
 Class to represent a room in the Adventure program
 
 Rooms have:
  ~ name
  ~ number (ranging from 1 - 98)
  ~ zero or one character
  ~ zero or more items
  ~ zero or one passage (or door) in each direction
		
 @author Todd Gerspacher
*/
public class Room
{
 private String name;

 /*
   NOTE: I choose to use a byte value to store room numbers because
     we know the maximum number for a room is going to be 98.
 */
 private byte number;

 private String character = null;

 /*
   NOTE: Although we don't know for sure how many items a room may store,
     the castle file should give us a good enough estimate were an array
     will be more appropriate than a LinkedList.     
 */
 private String[] items;

 /*
   NOTE: I choose to use an array of Passages, because we know there can
     only be one door for each wall.  In other words, one door per direction.
 */
 private Passage[] doors = new Passage[ Direction.NUM_DIRECTIONS ];

 private int numItems = 0;

 /**
  Constructor builds a Room object with the given name and number
  
  NOTE: I added this constructor because all Room objects should
   at least have a name and a number.

   @param name String containing the name of the Room
   @param number byte containing the number of the Room
 */
 public Room(String name, byte number)
 {
  this.name = name;
  this.number = number;
 }

 /**
  Method to set the name of this Room

  @param name String containing the name of this room    
 */
 public void setName(String name)
 {
  this.name = name;
 }

 /**
  Method to get the name of this Room

  @return String containing the name of this room    
 */
 public String getName()
 {
  return name;
 }

 /**
  Method to set the number of this Room.

  @param number byte containing the number for this Room
  @throws IllegalArgumentException if given number is less than 1
 */
 public void setNumber(byte number)
 {
   if(number < 1)
    throw new IllegalArgumentException("Room cannot have a number less than 1");

  this.number = number;
 }

 /**
  Method to get the number of this Room

  @return byte contains the number for this Room
 */
 public byte getNumber()
 {
  return number;
 }

 /**
  Method to set the character that is in this Room

  @param character String containing the character that is in this room    
 */
 public void setCharacter(String character)
 {
  this.character = character;
 }

 /**
  Method to get the character that is in this Room

  @param String containing the character that is in this room    
 */
 public String getCharacter()
 {
  return character;
 }

 /**
  Method to set the number of items to be stored in this Room.

  @param num int denoting the number of items to be stored in this Room
  @throws IllegalArgumentException if attempting to store a negative number of items
 */
 public void setNumItems(int num)
 {
  if( num < 0 )
   throw new IllegalArgumentException("Room cannot hold zero or less items");

  // special case: items array not yet initialized  
  if( items == null )
   items = new String[num];
		
  // create a temporary array with that will hold the given number of items
  String temp[] = new String[num];
  
  /*
   We may need to change numItems if there are currently too many items being
    stored then what the temporary array can handle.
  */
  numItems = (numItems > num ? num : numItems);  
  
  // copy the items currently stored into the temporary array
  System.arraycopy(items, 0, temp, 0, numItems);

  // reference the temporary array
  items = temp;
 }

 /**
  Method to get the number of items in this Room

  @return int the number of items in this Room
 */
 public int getNumItems()
 {
  return numItems;
 }

 /**
  Method to add item to this Room.
	
  @param item String containing item to be added to Room
 */
 public void addItem(String item)
 {
  // special case: items array not yet initialized
  if( items == null )
  {
   items = new String[1];
   items[numItems++] = item;
   return;
  }

  // increase the number of items we can store?
  if( numItems >= items.length )
   setNumItems( items.length + 1 );
		 
  items[numItems++] = item;	
 }

// public static final int SHIFT = 2;
 
 /**
  Method to remove an item from this Room

  @param item String containing item to be removed from Room
  @returns String containing item that was removed, null otherwise
 */
 public String removeItem(String item)
 {
  String removedItem = null;
  
  // special case: items array not yet initialized
  if( items == null )
   return removedItem;

  for(int i = 0; i < numItems; i++)
   if( items[i].equalsIgnoreCase(item) )
   {
    removedItem = items[i];

    // shift items past the item to be removed to the left in the array
    // for(int j = i; j < (numItems - SHIFT); j++)
    for(int j = i; j < (numItems - 1); j++)
     items[j] = items[j + 1];
     
    /*
      decrement numItems variable to reflect remove
      release item from items array
    */
    items[ (numItems--) - 1 ] = null;

    break;
   }

  return removedItem;
 }

 /**
  Method to get an item from this Room 
  based on its possition in the items array

  @param pos The position of the item in the items array
  @param String containing the item at the specified position, otherwise null
 */
 public String getItem(int pos)
 {
  if( (pos < 0) || (pos > numItems) )
   return null;

  return items[pos];
 }

 /**
  Method to get all the items in the room
  
  @return String containing all the items in the room, separated by a comma
 */
 public String getItems()
 {
  String str = "";
    
  for(int i = 0; i < numItems; i++)
   str = str + items[i] + ", ";

  // remove trailing ", " to make display prettier
  str = str.substring(0, str.lastIndexOf(", "));
  
  return str;
 }

 
 /**
  Method to add a door to this Room.
  If there already exists a door in the given Passage's direction,
   method will overwrite the old Passage and replace it with the new Passage.
 
  @param door Passage exisiting out of this room
  @throws IllegalArgumentException if Passage's origin is not this Room 
     or the Passage's destination is this Room (a loop)
 */
 public void addDoor(Passage door)
 {
  if( door.getOrigin() != this )
   throw new IllegalArgumentException("Attempt to add a door that starts at a different room.");

  if( door.getDestination() == this )
   throw new IllegalArgumentException("Attempt to add a door that loops back to the same room.");

  doors[ door.getDirection().ordinal() ] = door;
 }

 /**
  Method to get all the doors in the room (including non-doors which are null).
  
  @return Passage[] contains all the doors in the room
 */
 public Passage[] getDoors()
 {
  return doors;
 }

 /**
  Method to explore a door in a given direction

  @param Direction the direction to explore if there is a door
  @returns Room that door leads to, null otherwise
 */
 public Room explore(Direction direction)
 {
   if( doors[direction.ordinal()] != null )
    return doors[direction.ordinal()].getDestination();

   return null;
 }

 /**
  Method to represent a Room object in a String
  Will display the name of the room, followed by its number.
  Will either display the character that is in the room or nothing if there is no character.
  Will either display the items that are in the room or nothing if there is no character.
  Will either display the passages of the room or nothing if there are no passages.

  @returns String representation of this Room object
 */
 public String toString()
 {
  String str = name + " #" +  number;

  if( character != null )
   str = str + " character(" + character + ")";

  if(numItems > 0)
   str = str + " items(" + getItems() + ")";
  
  str = str + " Passages(";

  for(int i = 0; i < Direction.NUM_DIRECTIONS; i++)
   if( doors[i] != null )  
    str = str + doors[i].toString() + ", ";

  if( str.endsWith( " Passages(" ) )
   str = str.substring(0, str.lastIndexOf( " Passages("  ));
  else
  {
   // remove trailing ", " to make display prettier
   str = str.substring(0, str.lastIndexOf(", "));
   str = str + ")";
  }

  return str;
 }

 /**
  Method to test Room class.

  Test creation of a Room 11/29/05, 12/02/05
  Test setting and getting the name of a Room 11/29/05
  Test setting the number of the Room 11/29/05
  Test setting and getting the character in the Room 11/29/05
  Test setting the number of items in the Room 11/29/05
  Test adding more items than was previously determined by setNumItems method 12/02/05
  Test getting the number of items in the Room 11/29/05
  Test retrieving an item from the Room 12/03/05
  Test adding a door to the Room 11/29/05, 12/04/05
  Test exploration of passage 12/03/05
  Test toString method 12/02/05
  Test removing an item from Room 12/02/05
  Test changing the name of Room that is in a Passage 12/02/05
 */
 public static void main(String[] main)
 {
  // *** Test creation of a Room
  // Room room = new Room();  // 11/29/05
  Room room = new Room("Living Room", (byte) 2);  // 12/02/05

  // *** Test setting and getting the name of a Room
  room.setName("Kitchen");
  System.out.println("Room's name: " + room.getName() );

  // *** Test setting and getting the number of the Room
  room.setNumber( (byte) 1 ); 
  System.out.println( "Room's number: " + room.getNumber() );

  // *** Test setting and getting the character in the Room 11/29/05
  room.setCharacter("wizard");
  System.out.println( "Character in room: " + room.getCharacter() ); 

  // *** Test setting the number of items in the Room 11/29/05
  room.setNumItems(1);

  // *** Test adding more items than was previously determined by setNumItems method 12/02/05
  room.addItem("table");
  room.addItem("chair");
  room.addItem("candle");

  // *** Test getting the number of items in the Room 11/29/05
  System.out.println("Number of items in room: " + room.getNumItems() );

  // *** Test retrieving an item from the Room 12/03/05
  System.out.println("Item at position 0 in list: " + room.getItem(0));

  // *** Test adding a door to the Room NOTE: Need to create a second room 
  
  /*
  // 11/29/05
  Passage door = new Passage();

  door.setOrigin(room);
  door.setDestination( new Room("Living Room", (byte) 2) );
  door.setDirection( Direction.NORTH );
  */
  Passage door = new Passage(room, new Room("Living Room", (byte) 2),
   Direction.NORTH); // 12/04/05
    
  room.addDoor(door);

  // *** Test exploration of passage 12/03/05
  System.out.println("Explore north: " + room.explore(Direction.NORTH) );

  // *** Test toString method 12/02/05
 System.out.println( room.toString() );

  // *** Test removing an item from the Room 12/02/05  
  System.out.println("Removing item from room");
  room.removeItem("chair");
  System.out.println("Result after item was removed\n" + room.toString() );

  // *** Test changing the name of Room that is in a Passage 12/02/05
  System.out.println("Change the name of the room that is also stored in a passage");
  room.setName("Entrance Hall");
  System.out.println("Result of name change\n" + room.toString() );     
 }
}