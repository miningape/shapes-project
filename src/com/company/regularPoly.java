package com.company;

public class regularPoly extends Shape2d{
    private double partitionAngle;
    private double radius;

    public regularPoly( double x, double y, double side_length, int num_sides ) {
        this( x, y, side_length, num_sides, 0 );
    }
    public regularPoly( double x, double y, double side_length, int num_sides, double offset ) {
        super( x, y, num_sides, MathHelper.radiusFromSideLengthAndNumberOfPartitions( side_length, num_sides ), offset );

        // I really really hate having to call this math operation more than once, but java has forced my hand
        radius = MathHelper.radiusFromSideLengthAndNumberOfPartitions( side_length, num_sides );
        this.partitionAngle = 360.0 / num_sides;
    }

    @Override
    public double Area() {
        Vec2d[] points = this.getPoints();
        double totalArea = 0;

        // Split shape into a series of triangles and then add their areas for the total area
        for ( int i = 0; i < points.length; i++ ) {
            Vec2d cur_point = points[i];
            Vec2d next_point = points[ i % points.length ]; // Get the next element in the sequence, wrapping around

            // Area of Triangle = 1/2 (a*b) * sin( c ) where c: is the angle in between a and b
            // c will always be the partition angle, a and b will always be the radius
            double localTriangleArea = (( this.radius * this.radius ) / 2 ) * Math.sin( MathHelper.degToRad( this.partitionAngle ) );
            totalArea += localTriangleArea; // Add the area of this triangle to the total area
        }

        return totalArea;
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
