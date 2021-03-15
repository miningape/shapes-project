package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        //Rectangle rect = new Rectangle(0, 0, 1);
        Shape2d square = new Square(0, 0, 2, 45);
        Shape2d circle = new Circle( 10, 5, 3);
        Shape2d pent = new Pentagon(-5, -5, 4);
        Shape2d tri = new EqTriangle(-2 ,6, 3);
        Shape2d rect = new Rectangle(2, 3, 4, 5);

        pent.setPos(9, 10);
        tri.updatePos(2, 13);

        System.out.println( rect.Distance(circle) );
        System.out.println( square.Area() );
        System.out.println( pent.ContainsPoint( new Vec2d(9, 10) ) );
        System.out.println( tri.Perimeter() );
        System.out.println( tri.getPos() );
    }
}
