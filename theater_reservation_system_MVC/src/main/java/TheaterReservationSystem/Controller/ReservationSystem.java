package TheaterReservationSystem.Controller;

import TheaterReservationSystem.Model.Theater;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationSystem {

  public static void main(String[] args) {
    Interactive interactive = new Interactive();
    List<Integer> access = new ArrayList<>(Arrays.asList(1,3,5));
    Theater theater = new Theater("Roxy", access, 15, 10);
    interactive.interact(theater);
  }
}
