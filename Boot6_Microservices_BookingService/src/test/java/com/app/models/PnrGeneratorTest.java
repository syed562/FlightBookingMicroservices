package com.app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PnrGeneratorTest {

    @Test
    void testPnrGeneration() {
        String pnr1 = PnrGenerator.generatePnr();
        String pnr2 = PnrGenerator.generatePnr();

        assertNotNull(pnr1);
        assertNotNull(pnr2);
        assertNotEquals(pnr1, pnr2); // Should be different
        assertTrue(pnr1.startsWith("PNR")); // format check
    }
}
