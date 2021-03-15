package com.company;

public class Main {
    public static void main(String[] args) {
        //Rectangle rect = new Rectangle(0, 0, 1);
        Square rect = new Square(0, 0, 2);

        Vec2d centroid = new Vec2d(0, 0);

        try {
            centroid = MathHelper.centroid( rect.getPoints() );
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println( rect.ContainsPoint( new Vec2d( 1, 1 ) ) );
        System.out.println( rect );
    }
}
