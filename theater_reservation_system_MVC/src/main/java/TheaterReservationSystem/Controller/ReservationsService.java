package TheaterReservationSystem.Controller;

import TheaterReservationSystem.Model.Row;
import TheaterReservationSystem.Model.Seat;
import TheaterReservationSystem.Model.Theater;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represent the reservation service class
 */
public class ReservationsService {

  private Theater theater;
  private Boolean enoughSeats = true;
  private Integer reservedRow = -1;

  /**
   * Constructor of reservation service object
   * @param theater - given theater
   */
  public ReservationsService(Theater theater) {
    this.theater = theater;
  }

  /**
   * Get the theater
   * @return theater
   */
  public Theater getTheater() {
    return theater;
  }

  /**
   * Get if it has enough seats
   * @return boolean
   */
  public Boolean getEnoughSeats() {
    return enoughSeats;
  }

  /**
   * Get reserved row number
   * @return row number
   */
  public Integer getReservedRow() {
    return reservedRow;
  }

  /**
   * Take reservation
   * @param numOfReservation - number of reserved seats
   * @param reservedFor - name reserved for
   * @param needAccess - if need accessible seats
   */
  public void reserveRecommendSeats(Integer numOfReservation, String reservedFor,
      Boolean needAccess) {
    List<Row> sortedRows = new ArrayList<>(this.theater.getRowList());
    sortedRows = sortedRows.stream().sorted().collect(Collectors.toList());
    if (needAccess) {
      Collections.reverse(sortedRows);
    }

    Row updateRow = null;

    //Enter each row
    for (Row currentRow: sortedRows) {
      List<Seat> openSeats = currentRow.stream().filter(seat -> seat.getReservedFor() == null)
          .collect(Collectors.toList());

      //If this row has enough open seats
      if(openSeats.size() >= numOfReservation) {
        Integer reserveCount = numOfReservation;
        for (Seat currentSeat: currentRow) {
          if (reserveCount > 0 && currentSeat.getReservedFor() == null) {
            currentSeat.setReservedFor(reservedFor);
            reserveCount --;
          }
        }

        //Reserve this row
        updateRow = currentRow;
        this.reservedRow = currentRow.getRowNum();
        break;
      }
    }

    if (updateRow != null) {
      for (Seat seat: updateRow) {
        for (Seat originalSeat: this.theater.getRowList().get(updateRow.getRowNum() - 1)) {
          if(seat.getName().equals(originalSeat.getName())) {
            this.theater.getRowList().get(updateRow.getRowNum() - 1)
                .set(this.theater.getRowList().get(updateRow.getRowNum() - 1)
                    .indexOf(originalSeat), seat);
          }
        }
      }
    } else {
      enoughSeats = false;
    }
  }
}
