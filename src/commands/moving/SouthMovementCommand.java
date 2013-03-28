package commands.moving;

import stuff.Adventure;
import enums.Direction;

public class SouthMovementCommand extends AbstractMovementCommand {

  public SouthMovementCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    enterPassage(Direction.South);
  }
}
