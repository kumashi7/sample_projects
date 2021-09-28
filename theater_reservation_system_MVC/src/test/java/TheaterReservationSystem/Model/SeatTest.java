package TheaterReservationSystem.Model;

import static org.junit.Assert.*;

import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

public class SeatTest {
  public Seat testSeat, testSeat2;

  @Before
  public void setUp() throws Exception {
    testSeat = new Seat('A', null);
  }

  @Test
  public void getName() {
    assertTrue(testSeat.getName().equals('A'));
  }

  @Test
  public void getReservedFor() {
    assertTrue(testSeat.getReservedFor() == null);
  }

  @Test
  public void setReservedFor() {
    testSeat.setReservedFor("test");
    assertEquals("test", testSeat.getReservedFor());
  }

  @Test
  public void testEquals() {
    assertTrue(testSeat.equals(testSeat));
    assertFalse(testSeat.equals("null"));
    assertFalse(testSeat.equals(testSeat2));

    testSeat2 = new Seat('A', null);
    assertTrue(testSeat.equals(testSeat2));

    testSeat2 = new Seat('B', null);
    assertFalse(testSeat.equals(testSeat2));

    testSeat2 = new Seat('A', "test");
    assertFalse(testSeat.equals(testSeat2));
  }

  @Test
  public void testHashCode() {
    int hash = Objects.hash(testSeat.getName(), testSeat.getReservedFor());
    assertTrue(testSeat.hashCode() == hash);
  }

  @Test
  public void testToString() {
    String expectedString = "_";
    assertEquals(expectedString, testSeat.toString());
  }
}