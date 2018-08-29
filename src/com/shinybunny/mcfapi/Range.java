package com.shinybunny.mcfapi;

public class Range {

    private boolean hasMin;
    private double min;
    private boolean hasMax;
    private double max;

    private boolean isExact;
    private double exact;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
        this.hasMin = true;
        this.hasMax = true;
    }

    public Range(double value, boolean max) {
        if (max) {
            this.max = value;
            this.hasMax = true;
            this.hasMin = false;
        } else {
            this.min = value;
            this.hasMax = false;
            this.hasMin = true;
        }
    }

    public Range(double value) {
        this.exact = value;
        this.isExact = true;
        this.hasMin = false;
        this.hasMax = false;
    }

    public static Range between(double min, double max) {
        return new Range(min,max);
    }

    public static Range min(double min) {
        return new Range(min,false);
    }

    public static Range max(double max) {
        return new Range(max,true);
    }

    public static Range at(double value) {
        return new Range(value);
    }

    @Override
    public String toString() {
        if (isExact) {
            return exact == Math.floor(exact) ? (int)exact + "" : exact + "";
        }
        String s = "";
        if (hasMin) {
            String m = min == Math.floor(min) ? (int)min + "" : min + "";
            s += m;
        }
        s += "..";
        if (hasMax) {
            String m = max == Math.floor(max) ? (int)max + "" : max + "";
            s += m;
        }
        return s;
    }
}
