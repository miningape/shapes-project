package com.company;

public class MathHelper {
    public static double degToRad( double deg ) { return ( Math.PI / 180 ) * deg; }
    public static double radToDeg( double rad ) { return ( 180 / Math.PI ) * rad; }

    // ! Only works on regular shapes (side length + radius is equal all around the center)
    // There are as many partitions as sides: Partition angle = 360 / the number of sides
    public static double radiusFromSideLengthAndNumberOfPartitions( double sideLength, int numPartitions ) {
        // Basically cos rule: C^2= (B^2+A^2) - 2AB*cos y
        // Here y is partition angle: 360 / numPartitions (in Radians)
        // Since A = B = N is becomes: C^2 = 2N^2 - 2N^2*cos( 360/numPartitions )
        //                          => C^2 = ( 2 - 2*cos( 360/numPartitions ) ) * N^2
        // Here the sideLength is C, and we are calculating for N
        // So we get: 2N^2 = sideLength^2 / ( 1 - cos( 350/numPartitions ) )
        //         => N = sqrt( sideLength^2 / 2*(1 - cos( 360/numPartitions )) )

        return Math.sqrt( (sideLength*sideLength) / ( 2* (1 - Math.cos( degToRad(360.0/numPartitions) ) ) ) );
    }

    /**
     * IDK how its derived, but it seems to check out based on google, the formula is ( Ax(By-Cy) + Bx(Cy-Ay) + Cx(Ay-By) ) / 2
     * @param A,B,C Points that are the corners of a triangle
     * @return The area of the triangle formed by points A, B and C
     */
    public static double triangleAreaFromPoints( Vec2d A, Vec2d B, Vec2d C ) {
        return ( (A.x * (B.y - C.y)) +
                 (B.x * (C.y - A.y)) +
                 (C.x * (A.y - B.y)) ) / 2;
    }


    /**
     * @param A,B,C Points that are the corners of a triangle
     * @return  Literally the average of all the points
     */
    public static Vec2d triangleCentroid( Vec2d A, Vec2d B, Vec2d C ) {
        return A.add(B)
                .add(C)
                .scalarDiv(3);
    }

    /**
     * Based on code/ideas from https://fotino.me/calculating-centroids/
     * It calculates a centroid for a shape based on a weighted average of a polygons triangles when they
     * all share a point
     *
     * @param points an array of Vec2d points
     * @return the centroid of the shape defined in points
     */
    public static Vec2d centroid( Vec2d ...points ) throws Exception {

        if (points.length < 3) throw new Exception("MathHelper: centroid: @param points is less than 3 elements long. Centroid not calculated.");

        double total_area = 0.0;
        Vec2d centered_point   = new Vec2d( points[0].x, points[0].y );
        Vec2d average_centroid = new Vec2d( points[0].x, points[0].y ); // Get the point we will iterate around and start  our average on there

        // We subtract 2 here because n-2 triangles can be found around a single point in a shape with n points
        // Also because it would cause an array index out of bounds error
        for ( int i = 0; i < points.length - 2; i++) {
            Vec2d unstablePoint = points[i+1];      // Called unstable because it wont be used in next iteration
            Vec2d stablePoint   = points[i+2];      // Called stable because it forms the line we add/subtract the triangle over

            double area = triangleAreaFromPoints( centered_point, unstablePoint, stablePoint );

            if (area == 0) continue;                // The triangle is co-linear ( 3 points along a straight line ) so we can just skip it

            Vec2d centroid = triangleCentroid( centered_point, unstablePoint, stablePoint );

            // Add the centroid and area to the weighted averages
            average_centroid.x = ( (total_area * average_centroid.x) + (area * centroid.x) ) / ( total_area + area );
            average_centroid.y = ( (total_area * average_centroid.y) + (area * centroid.y) ) / ( total_area + area );

            total_area += area;
        }

        return average_centroid;
    }
}
