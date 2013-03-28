package commands;

import stuff.Adventure;
import stuff.Player;

public class ContentsCommand extends AbstractCommand {

  public ContentsCommand(Adventure adventure) {
    super(adventure);
  }

  @Override
  public void execute() {
    Player player = adventure.getPlayer();

    if(player.knapsack.isEmpty()) {
      System.out.println("You do not have anything in your knapsack.");
    }
    else {
      System.out.println("In your knapsack you have: " + player.knapsack);
    }
  }
}
