package items.interfacees;

import characters.BadGuy;

public interface Defensive {
  // You only need to defend against bad guys. (ie. Hag, Troll, Vampire, Werewolf)
  // Return true if Item kills BadGuy, false otherwise.
  public boolean useOn(BadGuy badGuy);
}
