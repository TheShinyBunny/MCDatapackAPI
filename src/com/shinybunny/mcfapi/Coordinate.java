package com.shinybunny.mcfapi;

public class Coordinate {

    private double value;
    private Modifier modifier;

    public Coordinate(double value, Modifier modifier) {
        this.value = value;
        this.modifier = modifier;
    }

    public static Coordinate relative(double value) {
        return new Coordinate(value,Modifier.RELATIVE);
    }

    public static Coordinate absolute(double value) {
        return new Coordinate(value,Modifier.ABSOLUTE);
    }

    public static Coordinate rotated(double value) {
        return new Coordinate(value,Modifier.ROTATED);
    }

    @Override
    public String toString() {
        String v = "" + value;
        if (value == Math.floor(value)) {
            v = (int)value + "";
        }
        if (v.equalsIgnoreCase("0")) {
            v = "";
        }
        switch (modifier) {
            case ROTATED:
                return "^" + v;
            case ABSOLUTE:
                return v.isEmpty() ? "0" : v;
            case RELATIVE:
                return "~" + v;
        }
        return super.toString();
    }

    public enum Modifier {
        ABSOLUTE, RELATIVE, ROTATED;
    }

}
