package commands.moving;

import java.io.FileNotFoundException;

import stuff.Castle;
import stuff.Player;
import stuff.Room;
import characters.Werewolf;
import characters.Wizard;
import enums.Direction;

public class EastMovementCommand extends AbstractMovementCommand {

  public void execute(Player player, Castle castle) {
    move(player, Direction.East, castle);
  }

  public static void main(String[] args) throws FileNotFoundException {
    Castle castle = Castle.loadFrom("./dat-files/castle.dat");
    Player player = new Player();
    EastMovementCommand command = new EastMovementCommand();

    System.out.println("-- Test Moving East (even though there's no Door in that Direction) --");
    player.setRoom(castle.findRoom("Dungeon"));
    command.execute(player, castle);

    System.out.println("-- Test Moving East (from Kitchen to Dining Room) --");
    Room kitchen = castle.findRoom("Kitchen");
    player.setRoom(kitchen);
    command.execute(player, castle);
    System.out.println("Is the Player in the Dining Room? " + (player.getRoom().getName().equalsIgnoreCase("Dining Room") ? "Yes" : "No"));

    System.out.println("-- Test Moving East (from Kitchen to Dining Room); where Kitchen has a Wizard --");
    kitchen.addOccupant(new Wizard());
    player.setRoom(kitchen);
    command.execute(player, castle);

    System.out.println("-- Test Moving East (from Kitchen to Dining Room); where Kitchen has a Werewolf --");
    kitchen.addOccupant(new Werewolf());
    player.setRoom(kitchen);
    command.execute(player, castle);
  }
}
