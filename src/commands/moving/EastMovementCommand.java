package commands.moving;

import stuff.Adventure;
import enums.Direction;

public class EastMovementCommand extends AbstractMovementCommand {

  public EastMovementCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    enterPassage(Direction.East);
  }
}
