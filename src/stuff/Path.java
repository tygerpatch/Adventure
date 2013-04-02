package stuff;

import enums.Direction;

public class Path {
  private Direction direction;
  private int roomNumber;

  public Path(Direction direction, int roomNumber) {
    this.direction = direction;
    this.roomNumber = roomNumber;
  }

  public Direction getDirection() {
    return direction;
  }

  public int getRoomNumber() {
    return roomNumber;
  }
}
