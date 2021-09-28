package TheaterReservationSystem.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a class of a Row
 */
public class Row extends ArrayList<Seat> implements Comparable{

  private Integer rowNum;
  private Boolean accessible;
  private Integer toCenter;

  public static final Integer ACCESS_PRI = 100;

  /**
   * Constructor of Row objects
   * @param numOfSeats - input number of seats in this row
   * @param accessible - if this row is accessible
   */
  public Row(Integer numOfSeats, Boolean accessible) {
    super();
    super.addAll(this.generateSeats(numOfSeats));
    this.accessible = accessible;
  }

  /**
   * Helper function to generate a list of seats according to
   * given number of seats
   * @param numOfSeats - given number of seats
   * @return list of seats
   */
  private List<Seat> generateSeats(Integer numOfSeats) {
    List<Seat> seats = new ArrayList<>();
    for (int i = 0; i < numOfSeats; i++) {
      seats.add(new Seat((char)('A' + i)));
    }
    return seats;
  }

  /**
   * Get the row number
   * @return row number
   */
  public Integer getRowNum() {
    return this.rowNum;
  }

  /**
   * Get if this row is accessible
   * @return boolean
   */
  public Boolean getAccessible() {
    return this.accessible;
  }

  /**
   * Set accessibility of this row
   * @param accessible boolean
   */
  public void setAccessible(Boolean accessible) {
    this.accessible = accessible;
  }

  /**
   * Set row number of this row
   * @param rowNum - given row number
   */
  public void setRowNum(Integer rowNum) {
    this.rowNum = rowNum;
  }

  /**
   * get the number to center row
   * @return to center number
   */
  public Integer getToCenter() {
    return toCenter;
  }

  /**
   * Set the number to center row
   * @param toCenter - to center number
   */
  public void setToCenter(Integer toCenter) {
    this.toCenter = toCenter;
  }

  /**
   * Set print info
   * @return string
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < super.size(); i++) {
      str.append(super.get(i).toString());
      str.append(" ");
    }
    String print = str.toString();
    if(this.accessible) {
      print = print.replace('_', '=');
    }

    if (this.rowNum!= null && this.rowNum <= 9)
      return this.rowNum + "  " + print + "\n";
    else return this.rowNum + " " + print + "\n";
  }

  /**
   * Compares this object with the specified object for order.  Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified
   * object.
   *
   * <p>The implementor must ensure
   * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))} for all {@code x} and {@code y}.  (This
   * implies that {@code x.compareTo(y)} must throw an exception iff {@code y.compareTo(x)} throws
   * an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
   * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for all {@code z}.
   *
   * <p>It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements
   * the {@code Comparable} interface and violates this condition should clearly indicate this fact.
   *  The recommended language is "Note: this class has a natural ordering that is inconsistent with
   * equals."
   *
   * <p>In the foregoing description, the notation
   * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
   * <i>signum</i> function, which is defined to return one of {@code -1},
   * {@code 0}, or {@code 1} according to whether the value of
   * <i>expression</i> is negative, zero, or positive, respectively.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   * or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it from being compared to
   *                              this object.
   */
  @Override
  public int compareTo(Object o) {
    if (o == null) {
      throw new NullPointerException();
    }
    if (!(o instanceof Row)) {
      throw new ClassCastException();
    }

    Row otherRow = (Row) o;
    return this.getToCenter().compareTo(otherRow.getToCenter()) + this.compareAccessible(otherRow);
  }

  /**
   * Helper function of comparing based on accessibility
   * @param otherRow - other object
   * @return int
   */
  private int compareAccessible(Row otherRow) {
    if (this.getAccessible().equals(otherRow.getAccessible())) {
      return 0;
    } else if (this.getAccessible() && !otherRow.getAccessible()) {
      return ACCESS_PRI;
    } else {
      return -ACCESS_PRI;
    }
  }

 /**{@inheritDoc}*/
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Row)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Row seats = (Row) o;
    return Objects.equals(rowNum, seats.rowNum) && Objects
        .equals(accessible, seats.accessible) && Objects.equals(toCenter, seats.toCenter);
  }

  /**{@inheritDoc}*/
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), rowNum, accessible, toCenter);
  }
}
