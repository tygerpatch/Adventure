package characters;

import java.util.Scanner;

import stuff.Player;

public abstract class NonPlayableCharacter {
  public abstract void interactWith(Player player);
  public abstract String getName();

  public static final NonPlayableCharacter fromString(String str, Scanner scanner) {
    if("Hag".equalsIgnoreCase(str)) {
      return new Hag(scanner);
    }

    if("Hunchback".equalsIgnoreCase(str)) {
      return new Hunchback();
    }

    if("Troll".equalsIgnoreCase(str)) {
      return new Troll();
    }

    if("Vampire".equalsIgnoreCase(str)) {
      return new Vampire();
    }

    if("Werewolf".equalsIgnoreCase(str)) {
      return new Werewolf();
    }

    if("Wizard".equalsIgnoreCase(str)) {
      return new Wizard();
    }

    return null;
  }
}
