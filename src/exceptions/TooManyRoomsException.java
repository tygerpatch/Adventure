package exceptions;

import stuff.Castle;

public class TooManyRoomsException extends IllegalStateException {

  private Castle castle;

  public TooManyRoomsException(Castle castle) {
    super("Castle has too many rooms.");
  }

  // Allow the client to query castle.
  public Castle getCastle() {
    return castle;
  }
}
