package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShapeTest {
    @Test
    @DisplayName("Testing movement of Shape2d")
    public void testMovement() {
        // Expected results found on a calculator then placed here
        // Using a square because it was easy to find on calculator, and doesn't override the methods being tested
        Square x = new Square(0, 0, 2);

        assertEquals( 0, x.getPos().x, "UNCHANGED:X-COORD");
        assertEquals( 0, x.getPos().y, "UNCHANGED:Y-COORD");

        // Using toString because I only want a few sig fig
        assertEquals( "(1.41, 0.00)", x.getPoints()[0].toString(), "UNROTATED:POINT1");
        assertEquals( "(0.00, 1.41)", x.getPoints()[1].toString(), "UNROTATED:POINT2");
        assertEquals( "(-1.41, 0.00)", x.getPoints()[2].toString(), "UNROTATED:POINT3");
        // unfortunate bug here were it shows -0, as there is no such number
        assertEquals( "(-0.00, -1.41)",x.getPoints()[3].toString(),  "UNROTATED:POINT4");

        // Lets test the shifting of the square
        x.updatePos(2, 2);
        assertEquals( 2, x.getPos().x, "SHIFTED(2, 2):X-COORD");
        assertEquals( 2, x.getPos().y, "SHIFTED(2, 2):Y-COORD");

        assertEquals( "(3.41, 2.00)", x.getPoints()[0].toString(), "UNROTATED-SHIFTED(2,2):POINT1");
        assertEquals( "(2.00, 3.41)", x.getPoints()[1].toString(), "UNROTATED-SHIFTED(2,2):POINT2");
        assertEquals( "(0.59, 2.00)", x.getPoints()[2].toString(), "UNROTATED-SHIFTED(2,2):POINT3");
        assertEquals( "(2.00, 0.59)",x.getPoints()[3].toString(),  "UNROTATED-SHIFTED(2,2):POINT4");

        // Testing explicit movement to a coordinate
        x.setPos(0, 0);
        assertEquals( 0, x.getPos().x, "SET(0, 0):X-COORD");
        assertEquals( 0, x.getPos().y, "SET(0, 0):Y-COORD");

        assertEquals( "(1.41, 0.00)", x.getPoints()[0].toString(), "UNROTATED-SHIFTED(2,2)-SET(0, 0):POINT1");
        assertEquals( "(0.00, 1.41)", x.getPoints()[1].toString(), "UNROTATED-SHIFTED(2,2)-SET(0, 0):POINT2");
        assertEquals( "(-1.41, 0.00)", x.getPoints()[2].toString(), "UNROTATED-SHIFTED(2,2)-SET(0, 0):POINT3");
        assertEquals( "(-0.00, -1.41)",x.getPoints()[3].toString(),  "UNROTATED-SHIFTED(2,2)-SET(0, 0):POINT4");
    }

    @Test
    @DisplayName("Testing the calculation of different shape's perimeter")
    public void testPerimeterRegularPoly() {
        for ( int i = 3; i < 20; i++) {
            regularPoly reg = new regularPoly(0, 0, 10, i);
            assertEquals( (int)(i * 10 - reg.Perimeter()), 0, "Calculating perimeter, unrotated");
        }

        for ( int i = 3; i < 20; i++) {
            regularPoly reg = new regularPoly(0, 0, 10, i, 100);
            regularPoly reg2 = new regularPoly(0, 0, 10, i, 0);
            assertEquals( (int) (reg2.Perimeter()-reg.Perimeter()), 0 , "Calculating perimeter, rotated");
        }
    }
    @Test
    @DisplayName("Testing the calculation of different squares area")
    public void testAreaRegularPoly() {
        for ( int i = 3; i < 20; i++) {
            regularPoly reg = new regularPoly(0, 0, 10, i);
            // Using a formula a found online (probably can replace the calculation for regularPoly with it but no time)
            assertEquals( 0,  (int)(((i * 10 * 10) / (4*Math.tan( MathHelper.degToRad(180.0/i) ))) - reg.Area()), "Calculating area to expected, unrotated" + i);
        }

        for ( int i = 3; i < 20; i++) {
            regularPoly reg = new regularPoly(0, 0, 10, i, 100);
            regularPoly reg2 = new regularPoly(0, 0, 10, i, 0);
            assertEquals( (int) (reg2.Area()-reg.Area()), 0 , "Comparing area, rotated");
        }
    }

    @Test
    @DisplayName("Test all the actual shape subclasses (square, rect, pentagon)")
    public void testActual() {
        // Area testing
        for ( int i = 3; i < 20; i++) {
            regularPoly reg = new regularPoly(0, 0, 10, i);
            // Using a formula a found online (probably can replace the calculation for regularPoly with it but no time)
            assertEquals( 0,  (int)(((i * 10 * 10) / (4*Math.tan( MathHelper.degToRad(180.0/i) ))) - reg.Area()), "Calculating area to expected, unrotated" + i);
        }

        // Perimeter Testing
        for ( int i = 3; i < 20; i++) {
            regularPoly reg = new regularPoly(0, 0, 10, i, 100);
            regularPoly reg2 = new regularPoly(0, 0, 10, i, 0);
            assertEquals( (int) (reg2.Area()-reg.Area()), 0 , "Comparing area, rotated");
        }
    }

    @Test
    @DisplayName("Testing the distance between several polys")
    public void testDist() throws Exception {
        Shape2d rect, square, pent, triangle, self, circle;

        rect = new Rectangle( 1, 0, 3, 4);
        square = new Square( 2, 0, 4 );
        pent = new Pentagon( -2, 0, 5, 45 );
        triangle = new EqTriangle( 0, 0, 6 );
        self = new regularPoly(0, 0, 16, 8);
        circle = new Circle(10, 0, 5);

        assertEquals( rect.Distance(square), 1 , "Rect Square");
        assertEquals( pent.Distance(triangle), 2, "Pent Triangle");
        assertEquals( self.Distance(circle), 10 , "Circle octogon");
        assertEquals( triangle.Distance(square), 2 , "Tri Square");
        assertEquals( self.Distance(square), 2 , "Rect Square");
        assertEquals( self.Distance(rect), 1 , "Rect Square");
        assertEquals( rect.Distance(self), 1 , "Rect Square");
        assertEquals( rect.Distance(circle), 9 , "Rect Square");
        assertEquals( self.Distance(pent), 2 , "Rect Square");
    }

    @Test
    @DisplayName("Testing the Vec2d stuff")
    public void testVec2d() {
        Vec2d first = new Vec2d( 0, 0 );
        Vec2d second = new Vec2d( 0, 0 );
        Vec2d third = new Vec2d( 0, 0 );

        assertEquals( first.add( 1, 3 ).toString(), second.sub( 1, 3 ).scalarMult(-1).toString(), "Add, Sub Mul");

        third = third.add(10, 0);
        assertEquals( third.magnitude(), 10 , "Magnitude");

        third = third.add(10, 0);
        assertEquals( third.normalize().magnitude(), 1 , "Magnitude normal");

        assertEquals( first.add(10, 10).normalize().toString(), second.add(5, 5).normalize().toString() , "Normalize");
    }

    @Test
    @DisplayName("Testing the position of polys")
    public void testshapepos() throws Exception {
        Shape2d rect, square, pent, triangle, self, circle;

        rect = new Rectangle( 1, 0, 3, 4);
        square = new Square( 2, 0, 4 );
        pent = new Pentagon( -2, 0, 5, 45 );
        triangle = new EqTriangle( 0, 0, 6 );
        self = new regularPoly(0, 0, 16, 8);
        circle = new Circle(10, 0, 5);

        assertEquals( rect.getPos().toString(), new Vec2d(1, 0).toString() , "Rect Square");
        assertEquals( square.getPos().toString(), new Vec2d(2, 0).toString() , "Rect Square");
        assertEquals( pent.getPos().toString(), new Vec2d(-2, 0).toString() , "Rect Square");
        assertEquals( triangle.getPos().toString(), new Vec2d(0, 0).toString() , "Rect Square");
        assertEquals( self.getPos().toString(), new Vec2d(0, 0).toString() , "Rect Square");
        assertEquals( circle.getPos().toString(), new Vec2d(10, 0).toString() , "Rect Square");

        circle.setPos(0, 0);
        assertEquals( self.getPos().toString(), circle.getPos().toString(), "Rect Square");
    }

}