package commands;

import stuff.Adventure;

public class QuitCommand extends AbstractCommand {

  public QuitCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    adventure.setStillPlaying(false);
  }
}
