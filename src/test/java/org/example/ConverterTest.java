package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConverterTest {

    private static final double EPS = 1e-9;

    @Test
    public void zeroC_is32F() {
        assertEquals(32.0, Converter.toFahrenheit(0.0), EPS);
    }

    @Test
    public void hundredC_is212F() {
        assertEquals(212.0, Converter.toFahrenheit(100.0), EPS);
    }

    @Test
    public void negative40C_isNegative40F() {
        assertEquals(-40.0, Converter.toFahrenheit(-40.0), EPS);
    }

    @Test
    public void roundTrip_fToC_toF() {
        double f = 77.0;
        double c = Converter.toCelsius(f);
        assertEquals(f, Converter.toFahrenheit(c), 1e-9);
    }
}

