package com.company;

public class Pentagon extends regularPoly {
    public Pentagon(double x, double y, double side_length) {
        this(x, y, side_length, 0);
    }
    public Pentagon(double x, double y, double side_length, double offset) {
        super(x, y, side_length, 5, offset);
    }
}
