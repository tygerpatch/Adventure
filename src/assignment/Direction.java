package assignment;
/**
 An enum type to represent a Direction 

 Available Direction types, with ordinal value in parentheses:

   NORTH(0)
   EAST(1)
   WEST(2)
   SOUTH(3)

 @author Todd Gerspacher
*/
public enum Direction
{
 NORTH, EAST, SOUTH, WEST;
 
 public static final int NUM_DIRECTIONS = 4;

 /**
   Method to test Direction enum

   Test creation of a Direction enum 12/02/05
   Test displaying name of a Direction to the console 12/02/05
   Test using the ordinal method 12/02/05
   Test using constant NUM_DIRECTIONS 12/02/05
 */
 public static void main(String[] args)
 {
   // *** Test creation of a Direction enum 12/02/05
   Direction north = Direction.NORTH;

  // ***  Test displaying name of a Direction to the console 12/02/05
  System.out.println( north.toString() );
  System.out.println( north.name() );

  // ***  Test using the ordinal method 12/02/05
  System.out.println( north.ordinal() );

  // *** Test using constant NUM_DIRECTIONS 12/02/05
  System.out.println( Direction.NUM_DIRECTIONS );
 }
}