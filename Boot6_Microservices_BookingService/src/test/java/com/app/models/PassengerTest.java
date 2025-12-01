package com.app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PassengerTest {

    @Test
    void testGettersAndSetters() {
        Passenger p = new Passenger();
        p.setPassengerId(1L);
        p.setPassenger_name("John Doe");
        p.setGender("Male");
        p.setAge(30);
        p.setSeatNumber("12A");
        p.setMealPreference("Veg");

        assertEquals(1L, p.getPassengerId());
        assertEquals("John Doe", p.getPassenger_name());
        assertEquals("Male", p.getGender());
        assertEquals(30, p.getAge());
        assertEquals("12A", p.getSeatNumber());
        assertEquals("Veg", p.getMealPreference());
    }

    @Test
    void testEqualsAndHashCode_Positive() {
        Passenger p1 = new Passenger();
        p1.setPassengerId(1L);
        p1.setPassenger_name("John");
        p1.setGender("Male");
        p1.setAge(30);
        p1.setSeatNumber("12A");
        p1.setMealPreference("Veg");

        Passenger p2 = new Passenger();
        p2.setPassengerId(1L);
        p2.setPassenger_name("John");
        p2.setGender("Male");
        p2.setAge(30);
        p2.setSeatNumber("12A");
        p2.setMealPreference("Veg");

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testEquals_Negative() {
        Passenger p1 = new Passenger();
        p1.setPassengerId(1L);

        Passenger p2 = new Passenger();
        p2.setPassengerId(2L); // different ID

        assertNotEquals(p1, p2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testEquals_DifferentObjectType() {
        Passenger p1 = new Passenger();
        assertNotEquals(p1, "NotPassengerObject");
    }

    @Test
    void testEquals_NullCase() {
        Passenger p1 = new Passenger();
        assertNotEquals(p1, null);
    }

    @Test
    void testCanEqual() {
        Passenger p = new Passenger();
        assertTrue(p.canEqual(new Passenger()));
        assertFalse(p.canEqual("String object"));
    }

    @Test
    void testToString() {
        Passenger p = new Passenger();
        p.setPassengerId(1L);
        String result = p.toString();
        assertNotNull(result);
        assertTrue(result.contains("1"));
    }
}
