package TheaterReservationSystem.View;

import TheaterReservationSystem.Model.Theater;

/**
 * Represent the class of Display
 */
public class Display {

  private Theater theater;

  /**
   * Constructor of display object
   * @param theater given theater
   */
  public Display(Theater theater) {
    this.theater = theater;
  }

  /**
   * Constructor of display object
   */
  public Display() {
  }

  /**
   * Show theater map
   */
  public void showMap() {
    System.out.println(theater);
  }

  /**
   * Show no seats error
   */
  public void notEnoughSeats() {
    System.out.println("Sorry, We don't have enough seats.");
  }

  /**
   * Show success info
   * @param name reserve name
   * @param num number of seats
   * @param row number of row
   */
  public void showReservation(String name, Integer num, Integer row) {
    System.out.println("I’ve reserved " + num.toString() + " seats for you at the "
        + this.theater.getName() + " in row " + row.toString() + ", "
        + name +".");
  }
}
