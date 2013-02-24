package assignment;
/**
 Class to represent Passages in Adventure program.
 
 Passages allow user to travel from a given Room 
  to another room in the castle.

 In a graph structure, a Passage is an edge.

 @author Todd Gerspacher
*/
public class Passage
{
 private Room origin;
 private Room destination;

 private Direction direction;

 /**
  Method to construct a Passage with the given information
  
  @param origin The room the Passage object starts at
  @param destination The room the Passage object starts at
  @param direction The room the Passage object starts at
 */
 public Passage(Room origin, Room destination, Direction direction)
 {
  setOrigin(origin);
  setDestination(destination);
  setDirection(direction);
 }
 
 /**
  Method to set the Room this Passage starts at (origin of edge)

  @param Room The Room this Passage should start at
 */
 public void setOrigin(Room room)
 {
  origin = room;
 }

 /**
  Method to get the Room this Passage starts at (origin of edge)

  @return Room The Room this Passage starts at
 */
 public Room getOrigin()
 {
  return origin;
 }

 /**
  Method to set the Room this Passage ends at (destination of edge)

  @param Room The Room this Passage should end at
 */
 public void setDestination(Room room)
 {
  destination = room;
 }

 /**
  Method to get the Room this Passage ends at (destination of edge)

  @return Room The Room this Passage ends at
 */
 public Room getDestination()
 {
  return destination;
 }

 /**
  Method to set the Direction of this Passage

  @param d The Direction this Passage should have
 */
 public void setDirection(Direction d)
 {
  direction = d;
 }

 /**
  Method to get the Direction of this Passage

  @return Direction The Direction of this Passage
 */
 public Direction getDirection()
 {
  return direction;
 }

 /**
  Method to return the String represetion of this Passage
  For example, "Kitchen NORTH to Living Room"

  NOTE: This will be used in the debug feature of the Adventure program

  @return String The String representation of this Passage
 */
 public String toString()
 {
  return origin.getName() + " " + direction + " to " + destination.getName();
 }

 /**
  Method to test Passage class

  Test creation of a Passage object 11/29/05, 12/04/05
  Test setting origin and gettting the origin of a Passage11/29/05, 12/03/05
  Test setting and getting the destination of a Passage11/29/05, 12/03/05
  Test setting and getting the direction of a Passage 11/29/05
  Test representing Passage in a String 11/29/05
 */
 public static void main(String[] args)
 {
  // *** Test creation of a Passage object
  // Passage p = new Passage(); // 11/29/05     
  Passage p = new Passage(new Room("Room 1", (byte)1), 
   new Room("Room 2", (byte)2), Direction.NORTH); // 12/04/05
    
  // *** Test setting origin and gettting the origin

  // Room origin = new Room(); // 11/29/05
  // origin.setName("Kitchen"); // 11/29/05
  // p.setOrigin( origin ); // 11/29/05

  p.setOrigin( new Room("Kitchen", (byte)1) ); // 12/03/05

  System.out.println( p.getOrigin().toString() );

  // *** Test setting and getting destination of a Passage

  // Room destination = new Room(); //11/29/05
  // destination.setName("Living Room"); //11/29/05
  //  p.setDestination( destination ); // 11/29/05

  p.setDestination( new Room("Living Room", (byte)2 ) );  //12/03/05

  System.out.println( p.getDestination().toString() );

  // *** Test setting and getting the direction of a Passage 11/29/05
  p.setDirection( Direction.NORTH );
 System.out.println( p.getDirection().toString() );

  // *** Test representing Passage in a String 11/29/05
  System.out.println( p.toString() );
 } 
 
}
