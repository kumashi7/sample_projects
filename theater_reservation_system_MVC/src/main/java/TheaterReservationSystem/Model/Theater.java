package TheaterReservationSystem.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represent a class of Theater
 */
public class Theater {

  private String name;
  private List<Row> rowList;
  private List<Integer> accessibleRows;

  /**
   * Constructor of theater objects
   * @param name - theater name
   * @param accessibleRows - list of accessible rows
   * @param numOfRows - total number of rows
   * @param seatsPerRow - number of seats per row
   */
  public Theater(String name, List<Integer> accessibleRows,
      Integer numOfRows, Integer seatsPerRow) {
    if (accessibleRows == null || accessibleRows.size() == 0)
      throw new IllegalArgumentException("Theater must have at least one accessible row.");
    this.name = name;
    this.accessibleRows = accessibleRows;
    this.generateRowList(numOfRows, seatsPerRow, accessibleRows);
  }

  /**
   * Generate row list based on inputs
   * @param numOfRows - number of rows
   * @param seatsPerRow - number of seats per row
   * @param accessibleRows - accessible rows
   */
  private void generateRowList(Integer numOfRows, Integer seatsPerRow,
      List<Integer> accessibleRows) {
    List<Row> newRowList = new ArrayList<>();
    for (int i = 1; i <= numOfRows; i++) {
      Row thisRow = new Row(seatsPerRow, false);
      thisRow.setRowNum(i);
      thisRow.setToCenter(Math.abs(numOfRows / 2 - i));
      newRowList.add(thisRow);
    }
    for (Integer row: accessibleRows) {
      newRowList.get(row - 1).setAccessible(true);
    }
    this.rowList = newRowList;
  }

  /**
   * Get name of this theater
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Get row list of this theater
   * @return row list
   */
  public List<Row> getRowList() {
    return rowList;
  }

  /**
   * Get accessible rows
   * @return row list
   */
  public List<Integer> getAccessibleRows() {
    return accessibleRows;
  }

  /**{@inheritDoc}*/
  @Override
  public String toString() {
    return this.rowList.stream().map(Row::toString).collect(Collectors.joining());
  }

  /**{@inheritDoc}*/
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Theater)) {
      return false;
    }
    Theater theater = (Theater) o;
    return Objects.equals(name, theater.name) && Objects
        .equals(rowList, theater.rowList) && Objects
        .equals(accessibleRows, theater.accessibleRows);
  }

  /**{@inheritDoc}*/
  @Override
  public int hashCode() {
    return Objects.hash(name, rowList, accessibleRows);
  }
}
