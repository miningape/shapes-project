package com.company;

public class Rectangle extends irregularPoly {
    private double width, height;


    /**
     * @param x Center of the rect, x component
     * @param y Center of the rect, y component
     * @param width Width of the rect
     * @param height Height of the rect
     * @throws Exception It will never throw because we are putting more than 2 things in everytime but java
     */
    public Rectangle( double x, double y, double width, double height ) throws Exception {
        super(  new Vec2d(x - (width / 2), y - (height / 2)),
                new Vec2d(x + (width / 2), y - (height / 2)),
                new Vec2d(x + (width / 2), y + (height / 2)),
                new Vec2d(x - (width / 2), y + (height / 2)) );

        this.width = width;
        this.height = height;
    }

    @Override
    public double Area() {
        return this.width * this.height;
    }

    @Override
    public double Perimeter() {
        return (2 * this.width) + (2 * this.height);
    }
}
