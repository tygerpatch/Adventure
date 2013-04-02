package stuff;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Room> {

  @Override
  public int compare(Room firstRoom, Room secondRoom) {
    return firstRoom.getDistance() - secondRoom.getDistance();
  }
}
