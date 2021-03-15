package com.company;

public class irregularPoly extends Shape2d {
    public irregularPoly(Vec2d... points) throws Exception {
        super(points);
    }

    @Override
    public double Area() {
        return 0;
    }

    @Override
    public double Perimeter() {
        return 0;
    }

    @Override
    public boolean Contains(Shape2d comparator) {
        return false;
    }

    @Override
    public double Distance(Shape2d comparator) {
        return 0;
    }
}
