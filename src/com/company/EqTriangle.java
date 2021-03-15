package com.company;

public class EqTriangle extends regularPoly {
    public EqTriangle(double x, double y, double side_length) {
        this(x, y, side_length, 0);
    }
    public EqTriangle(double x, double y, double side_length, double offset) {
        super(x, y, side_length, 3, offset);
    }
}
