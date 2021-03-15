package com.company;

import java.util.Arrays;

public abstract class Shape2d {
    private Vec2d pos;
    private Vec2d[] points;

    public Shape2d( double x, double y, Vec2d ...points ) {
        this.pos = new Vec2d( x, y );
        this.points = points;
    }


    /**
     * @param points List of points that define a shape
     * @throws Exception if the shape has less than 3 points (ie is a line or point and not a polygon)
     */
    public Shape2d( Vec2d ...points ) throws Exception  {
       this.pos = MathHelper.centroid( points );
       this.points = points;
    }

    public Shape2d( double x, double y, int numPoints, double regular_distance ) {
        this.pos = new Vec2d( x, y );
        this.points = new Vec2d[ numPoints ];

        double angleStep = 360.0 / numPoints;

        for ( int i = 0; i < numPoints; i++ ) {
            double curAngle = MathHelper.degToRad(angleStep * i);
            //                          X: Unit Coord       Y: Unit coord        Scale unit coords            Offset to be centered around this.pos
            this.points[i] = new Vec2d( Math.cos(curAngle), Math.sin(curAngle) ).scalarMult(regular_distance).add(this.pos);
        }
    }

    public Shape2d( double x, double y, int numPoints, double regular_distance, double angleOffset ) {
        this.pos = new Vec2d( x, y );
        this.points = new Vec2d[ numPoints ];

        double angleStep = 360.0 / numPoints;

        for ( int i = 0; i < numPoints; i++ ) {
            double curAngle = (angleStep * i) + angleOffset;
            curAngle = MathHelper.degToRad( curAngle );

            this.points[i] = new Vec2d( Math.cos(curAngle), Math.sin(curAngle) )
                                    .scalarMult(regular_distance)
                                    .add(this.pos);
        }
    }

    public Vec2d getPos () { return this.pos; }
    public void setPos ( double newX, double newY ) { this.pos = new Vec2d( newX, newY ); }
    public void setPos ( Vec2d newPos ) { this.pos = new Vec2d( newPos.x, newPos.y ); }

    public Vec2d[] getPoints() { return this.points; }

    public void updatePos ( double dX, double dY ) { this.pos = this.pos.add( dX, dY ); updatePoints( dX, dY ); }
    public void updatePos ( Vec2d vel ) { this.pos = this.pos.add( vel ); updatePoints( vel ); }

    private void updatePoints ( double dX, double dY ) { updatePoints( new Vec2d( dX, dY ) ); }
    private void updatePoints ( Vec2d diff ) {
        for ( int i = 0; i < this.points.length; i++ ) {
            points[i] = points[i].add( diff );
        }
    }

    public abstract double  Area();
    public abstract double  Perimeter();
    public abstract boolean Contains( Shape2d comparator );
    public abstract double  Distance( Shape2d comparator );

    @Override
    public String toString() {
        return String.format("Pos: " + this.pos.toString() + "\nPoints: " + Arrays.toString(this.points) );
    }
}
