package commands.moving;

import stuff.Adventure;
import enums.Direction;

public class WestMovementCommand extends AbstractMovementCommand {

  public WestMovementCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    enterPassage(Direction.West);
  }
}
