package com.company;

public class Square extends regularPoly{
    private double SIDE_LENGTH;

    public Square(double x, double y, double side_length) {
        this(x, y, side_length, 0.0);
    }
    public Square(double x, double y, double side_length, double offset) {
        super(x, y, side_length, 4, offset);

        this.SIDE_LENGTH = side_length;
    }

    // Overriding since there is a simple formula for the Area here
    @Override
    public double Area() {
        return this.SIDE_LENGTH * this.SIDE_LENGTH;
    }

    // Overriding again cus there is a simple formula
    @Override
    public double Perimeter() {
        return 4 * this.SIDE_LENGTH;
    }

    // Overriding so this uses the simple formula
    @Override
    public boolean ContainsPoint(Vec2d point) {
        return this.ContainsPoint(point, this.Area());
    }
}
