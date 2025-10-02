package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test void testFtoC() {
        assertEquals(0.0,  Converter.fToC(32.0), 1e-6);
        assertEquals(100.0, Converter.fToC(212.0), 1e-6);
    }

    @Test void testKtoC() {
        assertEquals(0.0,   Converter.kToC(273.15), 1e-6);
        assertEquals(100.0, Converter.kToC(373.15), 1e-6);
    }

    @Test void testCtoK() {
        assertEquals(273.15, Converter.cToK(0.0), 1e-6);
        assertEquals(373.15, Converter.cToK(100.0), 1e-6);
    }
}
