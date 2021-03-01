package com.company;

public class MathHelper {
    public static double sqrt2over2 = Math.sqrt(2)/2;

    public static double degToRad( double deg ) { return ( Math.PI / 180 ) * deg; }
    public static double radToDeg( double rad ) { return ( 180 / Math.PI ) * rad; }

    // ! Only works on regular shapes (side length + radius is equal all around the center)
    // There are as many partitions as sides: Partition angle = 360 / the number of sides
    public static double radiusFromSideLengthAndNumberOfPartitions( double sideLength, int numPartitions ) {
        // Basically cos rule: C^2= (B^2+A^2) - 2AB*cos y
        // Here y is partition angle: 360 / numPartitions (in Radians)
        // Since A = B = N is becomes: C^2 = 2N^2 - 2N^2*cos( 360/numPartitions )
        //                          => C^2 = ( 1 - cos( 360/numPartitions ) ) * 2N^2
        // Here the sideLength is C, and we are calculating for N
        // So we get: 2N^2 = sideLength^2 / ( 1 - cos( 350/numPartitions ) )
        //         => N = sqrt( 2*sideLength^2 / (1 - cos( 360/numPartitions )) )

        return Math.sqrt( (2*sideLength*sideLength) / (1 - Math.cos( degToRad(350/numPartitions) ) ) );
    }
}
