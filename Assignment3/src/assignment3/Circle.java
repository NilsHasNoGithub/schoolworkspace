/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.Color;
import java.util.Comparator;

/**
 *
 * @author nils
 */
public class Circle extends GeometricObject {

    private double radius;

    public Circle(double x, double y, double r) {
        super(x, y);
        this.radius = r;
    }

    @Override
    public double leftBorder() {
        return getAnchor().getX() - radius;
    }

    @Override
    public double rightBorder() {
        return getAnchor().getX() + radius;
    }

    @Override
    public double topBorder() {
        return getAnchor().getY() + radius;
    }

    @Override
    public double bottomBorder() {
        return getAnchor().getY() - radius;
    }

    @Override
    public double area() {
        return radius * radius * Math.PI;
    }

    @Override
    public String toString() {
        return ("Type: Circle")
                + ("Left Border: " + leftBorder() + "\n")
                + ("Right border: " + rightBorder() + "\n")
                + ("Top border: " + topBorder() + "\n")
                + ("Bottom border: " + bottomBorder() + "\n")
                + ("Radius: " + radius);
    }

}
