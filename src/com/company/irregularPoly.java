package com.company;

// Essentially a poly-line, is designed to be extended but can be used on its own
public class irregularPoly extends Shape2d {
    public irregularPoly(Vec2d... points) throws Exception {
        super(points);
    }

    @Override
    public double Area() {
        Vec2d[] points = this.getPoints();
        Vec2d algoPoint= points[0];
        double totalArea = 0;

        // Implicit: "if ( this.points.length < 3) return 0;"

        // Split shape into a series of triangles ( from one point on the perimeter to each other point ) and then add their areas for the total area
        for ( int i = 0; i < points.length-2; i++ ) {
            Vec2d unstablePoint = points[i+1];
            Vec2d stablePoint   = points[i+2];

            double localTriangleArea = MathHelper.triangleAreaFromPoints( algoPoint, unstablePoint, stablePoint );
            totalArea += localTriangleArea; // Add the area of this triangle to the total area
        }

        return totalArea;
    }

    @Override
    public double Distance(Shape2d comparator) {
        return this.getPos()
                .sub( comparator.getPos() )
                .magnitude();
    }

    @Override // Unimplemented
    public boolean Contains(Shape2d comparator) {
        return false;
    }

    @Override
    public boolean ContainsPoint(Vec2d point) {
        return this.ContainsPoint( point, this.Area() );
    }

}
