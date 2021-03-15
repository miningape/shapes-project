package com.company;

import java.util.Arrays;

/**
 * Base class for creating convex polygons
 * It handles moving the shape, and some basic calculations
 *
 * TODO implement the collision detection
 */
public abstract class Shape2d {
    private Vec2d pos;
    private Vec2d[] points;

    // Attributes/Methods for setting the accuracy of certain operations
    private double floatingPointAccuracy = 0.0001;
    public void setFloatingPointAccuracy(double floatingPointAccuracy) { this.floatingPointAccuracy = floatingPointAccuracy; }

    /**
     * For creating irregular polygons, should catch any case when there is > 3 points
     * @param points List of points that define a shape
     * @throws Exception if the shape has less than 3 points (ie is a line or point and not a polygon)
     */
    public Shape2d( Vec2d ...points ) throws Exception  {
       this.pos = MathHelper.centroid( points );
       this.points = points;
    }

    /**
     * For creating a straight line, dont necessarily see how useful it is right now
     * @param point1
     * @param point2
     */
    public Shape2d( Vec2d point1, Vec2d point2) {
        points = new Vec2d[]{ point1, point2 };
        pos = point1.add( point2 ).scalarDiv(2);
    }


    /**
     * For creating circles/a single point, can probably also be used in other cases but isnt
     * this is because vec2d does the same job but better for tracking 1 point
     * @param x
     * @param y
     */
    public Shape2d( double x, double y) {
        points = new Vec2d[0];
        pos = new Vec2d( x, y );
    }


    /**
     * For creating regular polygons
     * @param x
     * @param y
     * @param numPoints
     * @param regular_distance
     * @param angleOffset
     */
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

    // Very standard attribute getters
    public Vec2d getPos () { return this.pos; }
    public Vec2d[] getPoints() { return this.points; }
    public double getFloatingPointAccuracy() { return floatingPointAccuracy; }


    // Methods for moving the shape around
    public void setPos ( double newX, double newY ) { this.setPos( new Vec2d( newX, newY ) ); }
    public void setPos ( Vec2d newPos ) {
        Vec2d diff = newPos.sub( this.pos );    // Calculates the change in position
        this.updatePos( diff );                 // Propegates that change to the points
    }

    // Moves pos, then propegates to move all the points
    public void updatePos ( double dX, double dY ) { this.updatePos( new Vec2d( dX, dY ) ); }
    public void updatePos ( Vec2d vel ) { this.pos = this.pos.add( vel ); updatePoints( vel ); }

    private void updatePoints ( double dX, double dY ) { updatePoints( new Vec2d( dX, dY ) ); }
    private void updatePoints ( Vec2d diff ) {
        for ( int i = 0; i < this.points.length; i++ ) {
            points[i] = points[i].add( diff );
        }
    }


    /**
     * Finds the "center to center" euclidean distance between 2 shapes
     * @param comparator The shape we want to find the distance to
     * @return the distance from the current shape to comparator
     */
    public double Distance( Shape2d comparator ) {
        return this.pos
                .sub( comparator.getPos() )    // Subtract to get the vector going from the center of other shape to center of the other shape
                .magnitude();
    }

    /**
     * Should be only used by subclasses, inside an override of the "containsPoint( Vec2d )" Method
     * This is because the method relies on the Area of the shape, which is calculated differently in the subclasses
     * @param point The point we are checking
     * @param Area The area of the shape
     * @return Whether the point is within the shape or not
     */
    protected boolean ContainsPoint( Vec2d point, double Area ) {
        double total_area = 0;

        if ( this.points.length < 3 ) return false;

        for ( int i = 0; i < this.points.length; i++ ) {
            Vec2d cur_point = this.points[i];
            Vec2d next_point= this.points[ (i + 1) % this.points.length ];

            total_area += MathHelper.triangleAreaFromPoints( point, cur_point, next_point );
        }

        return (total_area + this.floatingPointAccuracy) > Area && (total_area - this.floatingPointAccuracy) < Area;
    }


    /**
     * Algorithm (slow) that will work on any shape that can be created
     * @return Perimeter/Circumference of the current shape
     */
    public double Perimeter() {
        double totalPerimeter = 0;

        if ( this.points.length < 3) return 0;

        for ( int i = 0; i < this.points.length; i++ ) {
            Vec2d cur_point = this.points[i];
            Vec2d next_point = this.points[ (i + 1) % this.points.length ];

            totalPerimeter += cur_point.sub( next_point ).magnitude();
        }

        return totalPerimeter;
    }

    // Abstract methods
    public abstract double  Area();                         // Area calculation different between regular/irregular also certain shapes have much faster/accurate formulas
    public abstract boolean ContainsPoint( Vec2d point );   // For overriding because i cant use this.Area in this class

    // TODO implement the separating axis theorem/algorithm: https://gamedevelopment.tutsplus.com/tutorials/collision-detection-using-the-separating-axis-theorem--gamedev-169
    public abstract boolean Contains( Shape2d comparator );

    // Overriding tostring so I can print my objects nicely
    @Override
    public String toString() {
        return String.format("Pos: " + this.pos.toString() + "\nPoints: " + Arrays.toString(this.points) );
    }
}
