package commands;

import stuff.Adventure;

public abstract class AbstractCommand {

  protected Adventure adventure;

  public AbstractCommand(Adventure adventure) {
    this.adventure = adventure;
  }

  public abstract void execute();
}
