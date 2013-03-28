package commands.moving;

import stuff.Adventure;
import enums.Direction;

public class NorthMovementCommand extends AbstractMovementCommand {

  public NorthMovementCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    enterPassage(Direction.North);
  }
}
