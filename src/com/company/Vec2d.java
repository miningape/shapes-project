package com.company;

public class Vec2d {
    public double x;
    public double y;

    public Vec2d ( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    public double magnitude() { return Math.sqrt( (x * x) + (y * y) ); }
    public double projection( Vec2d Other ) { return 0.0; }
    public double dotProduct( Vec2d Other ) { return 0.0; }
    public Vec2d normalVector() { return new Vec2d(0, 0); }

    public Vec2d normalize() {
        double mag = this.magnitude();
        return new Vec2d( this.x / mag, this.y / mag );
    }

    public Vec2d scalarMult( double scale ) { return new Vec2d( this.x * scale, this.y * scale ); }

    public Vec2d matrixMult( double r1c1, double r1c2,
                             double r2c1, double r2c2 ) {
        return new Vec2d( (r1c1 * this.x) + (r1c2 * this.y),
                          (r2c1 * this.x) + (r2c2 * this.y) );
    }

    // Basic Vector Addition
    public Vec2d add( double deltaX, double deltaY ) { return new Vec2d( this.x + deltaX, this.y + deltaY ); }
    public Vec2d add( Vec2d Other ) { return new Vec2d( this.x + Other.x, this.y + Other.y ); }

    // Basic Vector Subtraction
    public Vec2d sub( double deltaX, double deltaY ) { return new Vec2d( this.x - deltaX, this.y - deltaY ); }
    public Vec2d sub( Vec2d Other ) { return new Vec2d( this.x - Other.x, this.y - Other.y ); }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", this.x, this.y);
    }
}
