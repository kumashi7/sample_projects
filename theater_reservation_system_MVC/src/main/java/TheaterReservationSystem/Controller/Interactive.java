package TheaterReservationSystem.Controller;

import TheaterReservationSystem.Model.Theater;
import TheaterReservationSystem.View.Display;
import java.util.Scanner;

/**
 * Represent an Interactive class
 */
public class Interactive {

  private Integer reserveNumOfSeats;
  private String reserveFor;

  private Boolean done = false;
  private Boolean nameReceived = false;
  private Boolean needToWelcome = true;
  private Boolean needAccess = false;

  private Theater theater;
  private ReservationsService service;

  public final static String DONE = "done", SHOW = "show", RESERVE = "reserve", YES = "yes";

  /**
   * Start the interactive process
   * @param theater - given theater
   */
  public void interact(Theater theater) {
    this.theater = theater;
    while (!done) {
      if (needToWelcome) {
        String[] welcomeInput = welcome();
        evaluateInput(welcomeInput);
      } else {
        if (nameReceived) {
          this.displaySeat();
          needToWelcome = true;
          nameReceived = false;
        } else {
          askForName();
        }
      }
    }
  }

  /**
   * Start welcome process
   * @return user inputs
   */
  private String[] welcome() {
    Scanner console = new Scanner(System.in);

    System.out.println("What would you like to do?");
    return this.parseArgs(console.nextLine());
  }

  /**
   * Show seat map
   */
  private void displaySeat() {
    Display display = new Display(this.theater);
    display.showMap();
  }

  /**
   * Ask user if they need accessible seats
   */
  private void askForAccessible() {
    Scanner console = new Scanner(System.in);
    System.out.println("Do you need wheelchair accessible seats?");
    String result = console.nextLine();
    if (result.equals(YES)) {
      this.needAccess = true;
    }
  }

  /**
   * Ask user about reservation name
   */
  private void askForName() {
    System.out.println("What’s your name?");
    Scanner console = new Scanner(System.in);
    this.reserveFor = console.nextLine();

    this.askForAccessible();

    service = new ReservationsService(this.theater);
    service.reserveRecommendSeats(this.reserveNumOfSeats, this.reserveFor, this.needAccess);
    this.theater = service.getTheater();

    Display display = new Display(this.theater);
    if(!service.getEnoughSeats()) {
      display.notEnoughSeats();
      this.needToWelcome = true;
    } else {
      display.showReservation(this.reserveFor, this.reserveNumOfSeats, service.getReservedRow());
      this.nameReceived = true;
    }

    this.needAccess = false;
  }

  /**
   * Show error message when command is not correct
   */
  private void displayWhenInputInvalid() {
    System.out.println("Please enter a correct command.");
  }

  /**
   * Process input args
   * @param s - input string
   * @return processed string
   */
  private String[] parseArgs(String s) {
    return s.toLowerCase().split("\\s+");
  }

  /**
   * Evaluate inputs
   * @param input - given input
   */
  private void evaluateInput(String[] input) {
    if(input.length == 0)
      this.displayWhenInputInvalid();
    else if (input.length == 1) {
      if (input[0].equals(DONE)) {
        this.done = true;
      }
      else if (input[0].equals(SHOW)) {
        Display display = new Display(this.theater);
        display.showMap();
      }
      else {
        this.displayWhenInputInvalid();
      }
    } else if (input.length == 2) {
      if (input[0].equals(RESERVE)) {
        this.needToWelcome = false;
        this.reserveNumOfSeats = Integer.parseInt(input[1]);
      }
      else {
        this.displayWhenInputInvalid();
      }
    } else {
      this.displayWhenInputInvalid();
    }
  }
}
