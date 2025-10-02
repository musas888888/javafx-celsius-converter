package org.example;

/** Pure logic so we can unit test without JavaFX. */
public final class Converter {
    private Converter() {}

    /** Celsius -> Fahrenheit */
    public static double toFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    /** Fahrenheit -> Celsius (optional, handy for extra tests) */
    public static double toCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}
