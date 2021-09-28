package TheaterReservationSystem.Model;

import java.util.Objects;

/**
 * Represent a class of Seat
 */
public class Seat {

  private Character name;
  private String reservedFor;

  /**
   * Constructor of a Seat object
   * @param name - seat name
   * @param reservedFor - reserved name
   */
  public Seat(Character name, String reservedFor) {
    this.name = name;
    this.reservedFor = reservedFor;
  }

  /**
   * Constructor of a Seat object
   * @param name - seat name
   */
  public Seat(Character name) {
    this.name = name;
  }

  /**
   * Get the name of this seat
   * @return name of seat
   */
  public Character getName() {
    return this.name;
  }

  /**
   * Get the reserved info of this seat
   * @return reserve info
   */
  public String getReservedFor() {
    return this.reservedFor;
  }

  /**
   * Set reserve info of this seat
   * @param reservedFor - given info
   */
  public void setReservedFor(String reservedFor) {
    this.reservedFor = reservedFor;
  }

  /**{@inheritDoc}*/
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Seat)) {
      return false;
    }
    Seat seat = (Seat) o;
    return Objects.equals(name, seat.name) && Objects
        .equals(reservedFor, seat.reservedFor);
  }

  /**{@inheritDoc}*/
  @Override
  public int hashCode() {
    return Objects.hash(name, reservedFor);
  }

  /**{@inheritDoc}*/
  @Override
  public String toString() {
    if(this.reservedFor == null) {
      return "_";
    } else {
      return "X";
    }
  }
}
