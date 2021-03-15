package com.company;

public class Circle extends Shape2d {
    double radius;
    public Circle( double x, double y, double radius) {
        super( x, y );
        this.radius = radius;
    }

    @Override
    public double Area() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double Perimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override   // Unimplemented
    public boolean Contains(Shape2d comparator) {
        return false;
    }

    @Override
    public boolean ContainsPoint(Vec2d point) {
        // True if the distance between our circles center and the point being checked is less than the radius
        return this.getPos().sub( point ).magnitude() <= radius;
    }

    // Just so the method can be used along side other methods
    @Override
    public boolean ContainsPoint(Vec2d point, double Area) {
        return this.ContainsPoint(point);
    }
}
