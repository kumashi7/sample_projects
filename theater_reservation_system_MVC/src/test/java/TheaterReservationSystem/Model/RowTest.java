package TheaterReservationSystem.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RowTest {
  public Row testRow, testRow2;

  @Before
  public void setUp() throws Exception {
    testRow = new Row(1, false);
  }

  @Test
  public void getRowNum() {
    assertTrue(testRow.getRowNum() == null);
  }

  @Test
  public void getAccessible() {
    assertFalse(testRow.getAccessible());
  }

  @Test
  public void setAccessible() {
    testRow.setAccessible(true);
    assertTrue(testRow.getAccessible());
  }

  @Test
  public void setRowNum() {
    testRow.setRowNum(1);
    assertTrue(testRow.getRowNum() == 1);
  }

  @Test
  public void getToCenter() {
    assertTrue(testRow.getToCenter() == null);
  }

  @Test
  public void setToCenter() {
    testRow.setToCenter(0);
    assertTrue(testRow.getToCenter() == 0);
  }

  @Test
  public void testEquals() {
    assertTrue(testRow.equals(testRow));
    assertFalse(testRow.equals("null"));
    assertFalse(testRow.equals(testRow2));

    testRow2 = new Row(1, false);
    assertTrue(testRow.equals(testRow2));

    testRow2 = new Row(2, false);
    assertFalse(testRow.equals(testRow2));

    testRow2 = new Row(1, true);
    assertFalse(testRow.equals(testRow2));

    testRow2 = new Row(1, false);
    testRow.setRowNum(1);
    assertFalse(testRow.equals(testRow2));

    testRow2 = new Row(1, false);
    testRow.setToCenter(1);
    assertFalse(testRow.equals(testRow2));
  }

  @Test
  public void testToString() {
    String expectedString = "null _ \n";
    assertEquals(expectedString, testRow.toString());
  }

}