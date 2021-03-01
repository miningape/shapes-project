package com.company;

public class Rectangle extends Shape2d {
    private double width;
    private double height;

    public Rectangle(double x, double y, double widHei) {
        super( x, y, 10,  MathHelper.radiusFromSideLengthAndNumberOfPartitions( widHei, 10 ), 70 );
        // Generate square


        this.width = widHei;
        this.height = this.width;
    }

    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }

    @Override
    public double Area() {
        return this.width * this.height;
    }

    @Override
    public double Perimeter() {
        return (2 * this.width) + (2 * this.height);
    }

    @Override
    public boolean Contains(Shape2d comparator) {
        return false;
    }

    @Override
    public double Distance(Shape2d comparator) {
        return  0.0;
    }
}
