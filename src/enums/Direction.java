package enums;

public enum Direction {
  North, East, South, West;

  public static Direction fromChar(char ch) {
    if((ch == 'N') || (ch == 'n')) {
      return North;
    }

    if((ch == 'E') || (ch == 'e')) {
      return East;
    }

    if((ch == 'S') || (ch == 's')) {
      return South;
    }

    if((ch == 'W') || (ch == 'w')) {
      return West;
    }

    return null;
  }

  public String getName() {
    return this.name();
  }
}
