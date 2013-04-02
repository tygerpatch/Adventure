package stuff;

public class Door {
  private int destination = 0;

  public void setDestination(int destination) {
    this.destination = destination;
  }

  public int open() {
    return destination;
  }
}
