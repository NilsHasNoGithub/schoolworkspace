/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author nils
 */
public class Rectangle extends GeometricObject {

    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public double leftBorder() {
        return getAnchor().getX();
    }

    @Override
    public double rightBorder() {
        return getAnchor().getX() + width;
    }

    @Override
    public double topBorder() {
        return getAnchor().getY() + height;
    }

    @Override
    public double bottomBorder() {
        return getAnchor().getY();
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return ("Type: Circle")
                + ("Left Border: " + leftBorder() + "\n")
                + ("Right border: " + rightBorder() + "\n")
                + ("Top border: " + topBorder() + "\n")
                + ("Bottom border: " + bottomBorder() + "\n")
                + ("Width: " + width)
                + ("Height: " + height);
    }

}
