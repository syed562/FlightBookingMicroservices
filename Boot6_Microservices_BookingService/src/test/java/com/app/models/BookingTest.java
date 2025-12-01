package com.app.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void testGettersAndSetters() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setPnr("PNR123");
        booking.setFlightId(10L);
        booking.setUserName("John");
        booking.setEmailId("john@gmail.com");
        booking.setNumberOfSeats(2);
        booking.setTotalAmount(5000.0);
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        booking.setDepartureTime(LocalDateTime.now().plusDays(2));

        List<Passenger> passengers = new ArrayList<>();
        booking.setPassengers(passengers);

        assertEquals(1L, booking.getBookingId());
        assertEquals("PNR123", booking.getPnr());
        assertEquals(10L, booking.getFlightId());
        assertEquals("John", booking.getUserName());
        assertEquals("john@gmail.com", booking.getEmailId());
        assertEquals(2, booking.getNumberOfSeats());
        assertEquals(5000.0, booking.getTotalAmount());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(passengers, booking.getPassengers());
    }

    @Test
    void testEqualsAndHashCode_Positive() {
        Booking b1 = new Booking();
        b1.setBookingId(1L);
        b1.setPnr("PNR123");
        b1.setFlightId(10L);

        Booking b2 = new Booking();
        b2.setBookingId(1L);
        b2.setPnr("PNR123");
        b2.setFlightId(10L);

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testEquals_Negative() {
        Booking b1 = new Booking();
        b1.setBookingId(1L);

        Booking b2 = new Booking();
        b2.setBookingId(2L); // different bookingId

        assertNotEquals(b1, b2);
        assertNotEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testEquals_DifferentClass() {
        Booking booking = new Booking();
        assertNotEquals(booking, "not a booking");
    }

    @Test
    void testEquals_NullCase() {
        Booking booking = new Booking();
        assertNotEquals(booking, null);
    }

    @Test
    void testCanEqual() {
        Booking booking = new Booking();
        assertTrue(booking.canEqual(new Booking()));
        assertFalse(booking.canEqual("string object"));
    }

    @Test
    void testToString() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setPnr("P123");
        String output = booking.toString();
        assertNotNull(output);
        assertTrue(output.contains("1"));
        assertTrue(output.contains("P123"));
    }
}
