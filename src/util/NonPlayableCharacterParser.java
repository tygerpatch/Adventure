package util;

import java.util.Scanner;

import characters.Hag;
import characters.Hunchback;
import characters.NonPlayableCharacter;
import characters.Troll;
import characters.Vampire;
import characters.Werewolf;
import characters.Wizard;

public class NonPlayableCharacterParser {
  public static final NonPlayableCharacter parseString(String str, Scanner scanner) {
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
