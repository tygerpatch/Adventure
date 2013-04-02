package commands.moving;

import stuff.Castle;
import stuff.Player;
import enums.Direction;

public class SouthMovementCommand extends AbstractMovementCommand {

  public void execute(Player player, Castle castle) {
    move(player, Direction.South, castle);
  }
}
