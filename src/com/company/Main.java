package com.company;

public class Main {
    public static void main(String[] args) {
        //Rectangle rect = new Rectangle(0, 0, 1);
        regularPoly rect = new regularPoly(4, 2, 2, 4, 45);

        Vec2d centroid = new Vec2d(0, 0);
        try {
            centroid = MathHelper.centroid( rect.getPoints() );
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println( rect );
        System.out.println( centroid );
    }
}
