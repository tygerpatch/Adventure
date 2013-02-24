package assignment;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.StringTokenizer;

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

/**
 Adventure is a text based action/adventure game.

 "Player navigates a complex castle, picking up tools and treasures,...while warding off evil beings."
  (Adventure.doc, http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/index.htm , Dr. Liszka)

 The goal of the game is to find the King's crown and retrieve it for him.

 Note: Uses Entrance Hall as the starting room.

 @author Todd Gerspacher
*/
public class Adventure
{
 public static final String NO_MORE_ROOMS = "99";
 public static final String NO_MORE_PASSAGES = "99";

 public static final byte FULL_HEALTH = 100;
 public static final byte ELIXER_HP = 25;
 public static final byte BREAD_HP = 10;
 public static final byte WIZARD_HP = 5;
 public static final byte HAG_HP = 10;
 public static final byte TROLL_HP = 10;
 public static final byte VAMPIRE_HP = 25;
   
 /*
 SHIFT will be used in shift items in the knapsack

 I obtained the number 2 below through the following:
 - We only want to the shift the elements up to the 
    item before the last in the knapsack
 - The last item in the knapsack has an index of numItems - 1 
*/
public static final int SHIFT = 2;
 
 /*
  Since the String to display the command list is so long,
  we should create one String object containing the list 
  and then make it available to all Adventure objects.  
 */ 
 public static final String COMMAND_LIST = ""
  + "- Command List -\n"
  + "\n"
  + "N, n\tNorth - move through the north door if there is one\n"
  + "S, s\tSouth - move through the south door if there is one\n"
  + "E, e\tEast - move through the east door if there is one\n"
  + "W, w\tWest - move through the west door if there is one\n"
  + "\n"
  + "C, c\tContents - list the contents of the knapsack\n"
  + "U, u\tUse - use an item from the knapsack\n"
  + "T, t\tTake - pick up an item from the room\n"
  + "R, r\tRemove - remove an item from the knapsack\n"
  + "\n"   
  + "F, f\tFlee\n"
  + "Q, q\tQuit\n"
  + "D, d\tDebug\n";

 /*
  Although we know the maximum number for a room is 98
  (which also means there will be a maximum number of 98 rooms
  in the castle, if each room has a unique number), it would be
  better to use a LinkedList in this case.  The reason for this
  is that we may only store 2 rooms, which would waste a lot
  of memory.

  NOTE: The graph I will be using in this program is an Adjaceny List.
 */
 private LinkedList<Room> castle = new LinkedList<Room>();

 /* 
   BufferedReader object will be used in reading from a file
       and writing to the console 
 */
 private BufferedReader buffRead;

 public static final int MAX_ITEMS = 3;
 private String[] knapsack = new String[MAX_ITEMS];
 private int numItems = 0;
 private String used = null;

 private Room current;   // store the current room the user is in
 private Room start; // store room game starts at
 private byte health; // keep track of the users health
 
 private boolean woodenstake;
 private boolean silverbullet;
 
 /**
  Method attempts to read in the castle data from the file.
  Reads the rooms first. Then reads the passages. Finally it reads the items.

  @param filePath String containing the path of the castle file to use
  @throws IOException if an I/O exception occured during reading
 */
 public void readFile(String filePath) throws IOException
 {
  buffRead = new BufferedReader(new FileReader(filePath));
 
  readRooms();
  readPassages();
  readItems();

  buffRead.close();

  // *** Test that file was read correctly 12/03/05
  /*
  for(Room room: castle)
   System.out.println( room.toString() );
  */

  /* 
   Release BufferedReader and FileReader objects to
     the garbage collector, because we don't need to store
     a reference to them any more.
  */
  buffRead = null;
 } 

 /**
  Method reads in a room number and name, 
  until no more rooms is indicated (or an I/O exception occurs).

  @throws IOException if I/O exception occured during program execution 
 */
 private void readRooms() throws IOException
 {
  byte number; 
  String name; 
  String line; 
  Room room;   

  // read in the first line
  line = buffRead.readLine();
  
  // keep reading lines until no more rooms is indicated
  while( !line.equalsIgnoreCase(NO_MORE_ROOMS) )
  {
   /*
    The room's number should be indicated by the first character 
        up to the first space in the string.
     For example the line "1 Kitchen" indicates 1 should be the room's number
   */
   number = Byte.parseByte( line.substring(0, line.indexOf(' ')) );    

   /*
      The room's name should follow its number in the read in line
      For example the line "1 Kitchen" indicates Kitchen should be the room's name
   */
   name = line.substring( line.indexOf(' ') + 1, line.length() );
   
   // create a new room with the given name and number   
   room = new Room(name, number);

   castle.add(room);
					
   // read in the next line
   line = buffRead.readLine();
  }
 }

 /**
  Method reads in a room number, room number, and a direction.
  Keeps reading until no more passages is indicated  

  @throws IOException if I/O exception occured during program execution 
 */
 private void readPassages() throws IOException
 {
  byte from; 
  byte to; 
  char direction;
  String line; 
  StringTokenizer tokenizer;

  // read in the first line
  line = buffRead.readLine();

  // keep reading lines until no more passages is indicated
  while( !line.equalsIgnoreCase(NO_MORE_PASSAGES) )
  {
   /*
     Since the line will contain several pieces of information,
     using a StringTokenizer will be appropriate here.

     NOTE: Although StringTokenizer is a legacy class and
      it is recommeded that one use the split method of the String class,
      I see no reason to make the switch.
   */
   tokenizer = new StringTokenizer(line);
   
   // get passage information 
   from = Byte.parseByte( tokenizer.nextToken() );
   to = Byte.parseByte( tokenizer.nextToken() );
   direction = tokenizer.nextToken().charAt(0);

   add_door:  // search for the Room that Passage starts at
   for(Room origin: castle)
    if( origin.getNumber() == from )
    {
     // search for the Room that Passage ends at
     for(Room destination: castle)     
      if( destination.getNumber() == to )
      {
       // create passage and add it to the proper room
       if( (direction == 'N') || (direction == 'n') )
        origin.addDoor( new Passage(origin, destination, Direction.NORTH) );
       else if( (direction == 'S') || (direction == 's') )
        origin.addDoor( new Passage(origin, destination, Direction.SOUTH) );
       else if( (direction == 'E') || (direction == 'e') )
        origin.addDoor( new Passage(origin, destination, Direction.EAST) );
       else if( (direction == 'W') || (direction == 'w') )
        origin.addDoor( new Passage(origin, destination, Direction.WEST) );

       break add_door;
      }
    }

   // read in the next line
   line = buffRead.readLine();
  }
 } 

 /**
  Method reads in a room number, number of items in room, and items in room
  NOTE: If read in item is in fact a character, method will store it as so

  @throws IOException if I/O exception occured during program execution 
 */
 private void readItems() throws IOException
 {
  byte roomNumber; 
  int numItems;
  String item;
  String line;
  StringTokenizer tokenizer;

  // keep reading until end of stream is reached
  while( (line = buffRead.readLine()) != null )
  {
   /*
     Since the line will contain several pieces of information,
     using a StringTokenizer will be appropriate here.

     NOTE: Although StringTokenizer is a legacy class and
      it is recommeded that one use the split method of the String class,
      I see no reason to make the switch.
   */
   tokenizer = new StringTokenizer(line);
   
   // get the number of the Room items are to be added to
   roomNumber = Byte.parseByte( tokenizer.nextToken() );

   // get the number of items to be added to the Room
   numItems = Integer.parseInt( tokenizer.nextToken() );
      
   add_items:  // search for the Room to add items to
   for(Room room: castle)
    if( room.getNumber() == roomNumber )     
    {
     room.setNumItems( numItems );     

     while( tokenizer.hasMoreTokens() )
     {
      item = tokenizer.nextToken();
 
      if( isCharacter(item) )
       room.setCharacter(item);
      else
       room.addItem( item );
     }

     break add_items;      
    }
  }

 }

 /**
  Method to determine if an "item" is a character.
  @param item String containing the "item" to be checked
 */
 public static boolean isCharacter(String item)
 {
  if( item.equalsIgnoreCase("wizard") ) 
   return true;

  if( item.equalsIgnoreCase("hag") )
   return true;

  if( item.equalsIgnoreCase("troll") )
   return true;

  if( item.equalsIgnoreCase("vampire") )
   return true;

  if( item.equalsIgnoreCase("werewolf") )
   return true;

  if( item.equalsIgnoreCase("hunchback") )
   return true;

  return false;
 }

 /**
   Method to start the game at a Room with a specified name

  @param name String containing the name of the Room program
        should start at
  @throws IllegalStateException if there are no rooms in the castle
  @throws IllegalArgumentException if there are no rooms in the castle
   with the specified name
 */
 public void start(String name)
 {
  if( castle.size() == 0 )
   throw new IllegalStateException("There are no rooms in the castle.");

  // search for room in the castle with the specified name
  for(Room room: castle)
   if( room.getName().equalsIgnoreCase(name) )
   {
    start = room;
    break;
   }

  if( start == null )
   throw new IllegalArgumentException("There are no rooms in the castle with"
    + " the specified name(" + name + ")");
  
  buffRead = new BufferedReader(new InputStreamReader(System.in));
  health = FULL_HEALTH;
 }

  /**
   Method to actually play the game

   @throws IllegalStateException if there are no rooms in the castle
        or there is no starting room
 */
 public void play() throws IOException
 {
  if( start == null )
   throw new IllegalStateException("There is no starting room.");
  
  current = start;
  woodenstake = false;
  silverbullet = false;
  health = FULL_HEALTH;
  buffRead = new BufferedReader(new InputStreamReader(System.in));
  
  System.out.println("\nKing: Find me my crown!"); 

  while( health > 0 )
  {
   System.out.println("\nYou are in the " + current.getName());
   
   if( current.getNumItems() > 0 )
    System.out.println("Items in the room: " + current.getItems() );

   if( current.getCharacter() != null )
    System.out.println("There is also a " + current.getCharacter() + " in the room.");

   System.out.print("\nEnter command (H, h for help) : ");  
   process( buffRead.readLine().charAt(0) );
  }
 } 
 
 /**
   Method to process a command entered by user

   @param command char containing the command to be performed
 */  
 private void process(char command) throws IOException
 {
  switch(command)
  {
   // *** move through the castle
   case 'N': case 'n':    
   case 'S': case 's':
   case 'E': case 'e':
   case 'W': case 'w':
    move(command);
    break;
    
   // *** list contents of the knapsack
   case 'C': case 'c':
    contents();
    break;
		
   // *** use an item from the knapsack
   case 'U': case 'u':
    use();
    break;
				
   // *** pick up an item from the room
   case 'T': case 't':
    take();
    break;

   // *** remove an item from knapsack and put in room
   case 'R': case 'r':
    remove();
    break;

   // *** debug the graph
   case 'D': case 'd':
    debug();
    break;

   // *** flee to starting room
   case 'F': case 'f':
    flee();
    break;

   // *** display command list
   case 'H': case 'h':
    System.out.println( '\n' + COMMAND_LIST );
    break;

   // *** quit game		
   case 'Q': case 'q':
    health = 0;
    break;
    
   // *** bad command 
   default:
    System.out.println("I'm sorry. I don't understand that command.");
    break;
  }
 }

 /**
  Method that performs the action to move the user through a door
   in the current room in a given direction.  The user will remain in the
   current room if there are no doors in the given direction.  If there is
   a door in the given direction, the method will perform the action that
   the character in the current room should perform before moving the user into
   the new room.
  
  @param ch char containing the direction to move to
  @throws IOException if an I/O exception occured
 */
 private void move(char ch) throws IOException
 {
  Direction direction = Direction.NORTH;
  Room temp;
  
  switch(ch)
  {
   case 'N': case 'n':
    direction = Direction.NORTH;
    break;
   case 'S': case 's':
    direction = Direction.SOUTH;
    break;
   case 'W': case 'w':
    direction = Direction.WEST;
    break;
   case 'E': case 'e':
    direction = Direction.EAST;
    break;    
  }
  
  if( (temp = current.explore(direction)) != null )
  {
   doCharacter();
   current = temp;
   used = null;
   
   if( current == start )
    checkWinner();
  }
 }     
 
 /**
  Method to check if user has won the game
  Game is won if user has the crown in there
   knapsack.
 */
 private void checkWinner()
 {
  // indicate game should end
  health = (byte)(health - FULL_HEALTH);

  for(int i = 0; i < numItems; i++)
   if( knapsack[i].equalsIgnoreCase("crown") )
   {
    System.out.println("\nKing: Hurray! You found it!");
    return;
   }

  System.out.println("\nKing: Where's my crown!\n");
  System.out.println("Your head is chopped off by the King's executioner.  There's a lot of blood");
 }

 /**
  Method to display the contents of the knapsack
 */
 private void contents()
 {
  if( numItems == 0 )
  {
   System.out.println("\nYou do not have anything in your knapsack.");
   return;
  }
  
  String contents = "\nIn your knapsack you have: ";

  for(int i = 0; i < numItems; i++)
   contents = contents + knapsack[i] + ", ";

  contents = contents.substring(0, contents.lastIndexOf(", "));
  
  System.out.println( contents );
 }
		
 /**
  Method that allows the user to choose an item from the knapsack to use
 */
 private void use() throws IOException
 {
  if( numItems == 0 )
  {
   System.out.println("\nYou do not have anything in your knapsack.");
   return;
  }
  
  System.out.println("");
   
  for(int i = 0; i < numItems; i++)
   System.out.println("#" + (i + 1) + " - " + knapsack[i]);
  
  System.out.print("\nEnter the number of the item to use: ");
  int position = Integer.parseInt(buffRead.readLine()) - 1;  
 
  // safe gaurd against indexing an element that is out of bounds
  if((position < 0) || (position > numItems))
   return;

  String item = knapsack[position];

  if( item.equalsIgnoreCase("jewel") || item.equalsIgnoreCase("tome") ||
   item.equalsIgnoreCase("goblet") || item.equalsIgnoreCase("crown") )
  {
   System.out.println("\nThat item cannot be used here.");
   return;
  }

  System.out.println("\nYou have choosen to use the " + item);

  //NOTE: silverbullet and woodenstake may only be used in the program
  if( item.equalsIgnoreCase("woodenstake") )
   if(woodenstake)
   {
    System.out.println("Unfortunately you have already used the the " + item);    
    return;
   }
   else
    woodenstake = true;

  if( item.equalsIgnoreCase("silverbullet") )
   if(silverbullet)
   {
    System.out.println("Unfortunately you have already used the the " + item);    
    return;
   }
   else
    silverbullet = true;
  
  if( item.equalsIgnoreCase("elixer") )
  {
   health = (byte)(health + ELIXER_HP);
   health = (health > FULL_HEALTH ? FULL_HEALTH : health);
   System.out.println("Health = " + health);
  }

  if( item.equalsIgnoreCase("bread") )
  {
   health = (byte)(health + BREAD_HP);
   health = (health > FULL_HEALTH ? FULL_HEALTH : health);
   System.out.println("Health = " + health);
  }
  
  used = used + " " + item;

  if( !item.equalsIgnoreCase("club") && !item.equalsIgnoreCase("garlic") )
  {     
   for(int i = position; i < (numItems - 1); i++)
    knapsack[i] = knapsack[i + 1];
     
   knapsack[ (numItems--) - 1 ] = null;
  }
  
  if(item.equalsIgnoreCase("woodenstake") && ((current.getCharacter() != null) 
   && (current.getCharacter().equalsIgnoreCase("vampire"))))
  {
   System.out.println("\nYou were able to kill the vampire with the " 
    + "wooden-stake.");
   current.setCharacter(null); 
  }
  
  if(item.equalsIgnoreCase("silverbullet") && ((current.getCharacter() != null) 
   && (current.getCharacter().equalsIgnoreCase("werewolf"))))
  {
   System.out.println("\nYou were able to kill the werewolf "
    + "with a silver bullet.");   
   current.setCharacter(null); 
  }
  
}
 

 

 /**
  Method that allows user to select an item to take from the room
 */
 private void take() throws IOException
 {
  if( current.getNumItems() == 0 )
  {
   System.out.println("\nThere are no items to take from the room.");
   return;
  }

  if( numItems == MAX_ITEMS )
  {
   System.out.println("\nYour knapsack is full."
    + "\n\nIf you still want to take an item from a room, you will need to use"
    + "\nthe remove or use command to free some space in your knapsack.");
   return;
  }
  
  System.out.println("");
  
  for(int i = 0; i < current.getNumItems(); i++)
   System.out.println("#" + (i + 1) + " - " + current.getItem(i));

  System.out.print("\nEnter the number of the item to take: ");
  int i =  Integer.parseInt(buffRead.readLine()) - 1;
  
  if((i < 0) || (i > current.getNumItems()))  
   return;

  String item = current.getItem(i);
  
  if( item.equalsIgnoreCase("nothing") || item.equalsIgnoreCase("table")
   || item.equalsIgnoreCase("candle") )
  {
    System.out.println("\nThat item may not be removed from the room");
    return;
  }  

  current.removeItem(item);
  knapsack[numItems++] = item;  
 }


 /**
  Method that allows the user to remove an item from the knapsack and put in room
 */
 private void remove() throws IOException
 {
  if( numItems == 0 )
  {
   System.out.println("\nYou do not have anything in your knapsack.");
   return;
  }
  
  System.out.println("");
  
  for(int i = 0; i < numItems; i++)
   System.out.println("#" + (i + 1) + " - " + knapsack[i]);

  System.out.print("\nEnter the number of the item to remove: ");
  
  int index = Integer.parseInt( buffRead.readLine() ) - 1;

  if( (index < 0) || (index > numItems) )
    return;
  
  current.addItem( knapsack[index] );

  for(int i = index; i < (numItems - 1); i++)
   knapsack[i] = knapsack[i + 1];
   
  knapsack[ (numItems--) - 1 ] = null;
 }

 /**
  Method to debug the castle

  "Output should be a list of rooms and connections, one per line
    example: kitchen north to living room"
  (Adventure.doc, http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/index.htm , Dr. Liszka)
 */
 private void debug()
 {
   for(Room room: castle)
    for(Passage door: room.getDoors())
     if(door != null)
      System.out.println( door.toString() );
 }

  /**
  Method that causes the user to flee to the starting room.
  Will display each room that is visited to the console,
   but will not display the room's items or character.
 */
 private void flee()
 {
  Passage[] paths = dijkstra();
  Room temp = start;
  String strPath = "";
  String path[];
  int index;

  /*
   Begin at the starting room and work backwords
   until the current room is reached.
  */
  while(temp != current)
  {
   index = castle.indexOf(temp);
   strPath = strPath + " " + index;
   temp = paths[index].getOrigin();
  }

  /*
   Convert this path into an array and then traverse
   backwords through it  to flee from the current room
   to the starting room.
  */
  path = strPath.split(" ");
  
  System.out.println("");
  
  for(int i = (path.length - 1); i > 0; i--)
  {
   index = Integer.parseInt( path[i] );
   System.out.println("You flee from the " + paths[index].toString());    
  }

  // check if user has won the game
  checkWinner();
 }

 /**
  This method determines the shortest path from the current room
    to all the rooms in the castle.  Uses Dijkstra's Shortest Path algorithm

  @returns Passage[] Passages to take to get to a room.
       NOTE: The index of a Room in the castle LinkedList,
         is the same index in the Passage[].
 */
 private Passage[] dijkstra()
 { 
  Passage[] path = new Passage[castle.size()];
  byte[] distance = new byte[castle.size()];
  boolean[] included = new boolean[castle.size()];
  int min;
  int adjacent;

  // *** initialization phase
  for(int i = 0; i < castle.size(); i++)
  {
   path[i] = null;
   included[i] = false;

    /*
      Although dijkstra uses infinity to represent the worst case
      distance (that is you can never reach the vertex), infinity 
      obviously cannot be expressed as a number.  A more 
      practical number to use instead would be one large enough
      to store the distance obtained from visiting all the rooms
      in the graph once.

      Since we already know the maximum number of rooms 
      a castle can have 97.  (That is, if each room in the castle has 
      a unique number). And each path in the castle has an equal weight of 1.

      Therefore the number we should use for ininifity
       is the value of Byte.MAX_VALUE ((2 ^ 7) - 1 = 127)
    */
   distance[i] = Byte.MAX_VALUE;
  }

  min = castle.indexOf( current );
  
  /*
    Although making the distance to get to the current Room 
    as zero is not necessary in this implementation of Dijkstra,
    it is added here to keep with the tradition.
  */
  distance[min] = 0;  

  // *** iteration phase
  do
  {
   included[min] = true;

   for(Passage door: castle.get(min).getDoors())
    if( door != null )
    {
     adjacent = castle.indexOf( door.getDestination() );
     
     if( distance[adjacent] > (distance[min] + 1) )
     {
      distance[adjacent] = (byte)(distance[min] + 1);
      path[adjacent] = door;
     }
    }

   // search for the next smallest distance
   min = -1;

   for(int i = 0; i < castle.size(); i++)
    if((included[i] == false) && ((min == -1) || (distance[min] > distance[i])))
     min = i; 

  }while(min != -1);

  return path;
 }

 /**
  Method that performs the actions that the character
   in the current room should take.
  
  @throws IOException if an I/O error occured
 */
 private void doCharacter() throws IOException
 {
  String character = current.getCharacter();

  if( character == null )
   return;

  if( character.equalsIgnoreCase("wizard") )
   doWizard();

  if( character.equalsIgnoreCase("hag") )
   doHag();

  if( character.equalsIgnoreCase("troll") )
   doTroll();

  if( character.equalsIgnoreCase("vampire") )
   doVampire();

  if( character.equalsIgnoreCase("werewolf") )
   doWerewolf();

  if( character.equalsIgnoreCase("hunchback") )
   doHunchback();
 }

 /**
  Method to perform the actions that a Wizard should take
 */
 private void doWizard()
 {
  if( numItems < MAX_ITEMS )
  {
   System.out.println("\nThe wizard gives you a spell.");
   knapsack[numItems++] = "spell";
  }
  
  if( health < FULL_HEALTH )
  {
   System.out.println("\nThe wizard was able to heal some of your wounds.");

   health = (byte)(health + WIZARD_HP);
   health = (health > FULL_HEALTH ? FULL_HEALTH : health);
   System.out.println("\nHealth = " + health);
  }
 }

 /**
  Method to perform the actions that a Hag should take
 */
 private void doHag() throws IOException
 {
  System.out.println("\nThe Hag demands you give her something.");

  if(numItems == 0)
  {
   System.out.println("Unfortunately you cannot give her anything.\n\n"
    + "You recieve damage from the Hag.");

   health = (byte)(health - HAG_HP);
   System.out.println("Health = " + health);
   return;
  }

  int index = -1;

  while((index < 0) || (index > current.getNumItems()))
  {
   System.out.println("");
      
   for(int i = 0; i < numItems; i++)
    System.out.println("#" + (i + 1) + " - " + knapsack[i]);

   System.out.print("\nEnter the number of the item to give to Hag: ");   
   index =  Integer.parseInt(buffRead.readLine()) - 1;
  }  
  
  current.addItem( knapsack[index] );

  for(int i = index; i < (numItems - 1); i++)
   knapsack[i] = knapsack[i + 1];
     
  knapsack[ (numItems--) - 1 ] = null;
 }

 /**
  Method to perform the actions that a Troll should take
 */
 private void doTroll()
 {
   if( used != null )
    for(String item: used.split(" "))       
      if( item.equalsIgnoreCase("club") || item.equalsIgnoreCase("spell") ) 
      {
       System.out.println("\nYou were able to get past the Troll "
         + "with a " + item);
       return;
      }

  System.out.println("\nThe Troll bruises you pretty badly as you leave the room.");
  health = (byte)(health - TROLL_HP);
  System.out.println("\nHealth = " + health);
 }

 /**
  Method to perform the actions that a Vampire should take
 */
 private void doVampire()
 {
   if( used != null )
    for(String item: used.split(" "))       
      if( item.equalsIgnoreCase("garlic") )
      {
       System.out.println("\nYou were able to slip past the vampire with " 
        + "some garlic.");
       return;
      }

  System.out.println("\nThe Vampire sucks your blood.");
  health = (byte)(health - VAMPIRE_HP);
  System.out.println("\nHealth = " + health); 
 }

 /**
  Method to perform the actions that a Werewolf should take
 */
 private void doWerewolf()
 {
   if( used != null )
    for(String item: used.split(" "))       
      if( item.equalsIgnoreCase("spell") )
      {
       System.out.println("\nYou were able to slip past the werewolf with a spell.");
       return;
      }

  System.out.println("\nOh no, the Werewolf has bitten you!\n\nYou died.");
  health = (byte)(health - FULL_HEALTH);
 }

 /**
  Method to perform the actions that a Hunchback should take
 */
 private void doHunchback()
 {
  if(numItems == 0)
  {
   System.out.println("\nThe hunchback just smiles at you as you walk out the door.");
   return;
  }

  System.out.println("\nThe hunchback takes an item from your knapsack.");

  int index = (int)(numItems * Math.random());  
    
  current.addItem( knapsack[index] );
    
  for(int i = index; i < (numItems - 1); i++)
   knapsack[i] = knapsack[i + 1];
     
  knapsack[ (numItems--) - 1 ] = null;  
 }

 /**
  Main starting point of the console version of the Adventure program
 
  NOTE: Assumes there is a castle.dat file in the relative directory and
   that there will be a room named Entrance Hall in the castle.
  
  @param args An array of Strings, has no effect on program
  @throws IOException I/O exception that occured during program execution
 */
 public static void main(String[] args) throws IOException
 {
  Adventure game = new Adventure();
   
  game.readFile("castle.dat");
  game.start("Entrance Hall");
  game.play();
 }

}


