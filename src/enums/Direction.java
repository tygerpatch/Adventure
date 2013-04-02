package enums;

public enum Direction {
  North, East, South, West;

  // ex. Direction.North.nextClockwise() -> Direction.East
  public Direction nextClockwise() {
    if(Direction.North == this) {
      return Direction.East;
    }

    if(Direction.East == this) {
      return Direction.South;
    }

    if(Direction.South == this) {
      return Direction.West;
    }

    // if(Direction.West == this)...
    return Direction.North;
  }

  // ex. Direction.valueOf('N') -> Direction.North
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

  public static Direction fromInteger(int i) {
    if(Direction.North.ordinal() == i) {
      return Direction.North;
    }

    if(Direction.East.ordinal() == i) {
      return Direction.East;
    }

    if(Direction.South.ordinal() == i) {
      return Direction.South;
    }

    if(Direction.West.ordinal() == i) {
      return Direction.West;
    }

    return null;
  }

  public String getName() {
    return this.name();
  }
}
