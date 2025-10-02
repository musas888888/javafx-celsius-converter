package org.example;

/** Pure logic for unit tests (no JavaFX). */
public final class Converter {
    private Converter() {}

    /** Fahrenheit -> Celsius */
    public static double fToC(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    /** Kelvin -> Celsius */
    public static double kToC(double k) {
        return k - 273.15;
    }

    /** Celsius -> Kelvin */
    public static double cToK(double c) {
        return c + 273.15;
    }

    /** Celsius -> Fahrenheit (sinulla jo GUI:ssa, pidetään täälläkin) */
    public static double cToF(double c) {
        return (c * 9.0 / 5.0) + 32.0;
    }
}

