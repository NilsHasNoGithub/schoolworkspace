/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.Comparator;

/**
 *
 * @author nils
 */
public abstract class GeometricObject implements Geometric, Comparable<GeometricObject> {
    private Vector2D anchor;
    
    
    public GeometricObject(double x, double y){
        anchor=new Vector2D(x, y);
    }
    
    @Override
    public int compareTo(GeometricObject other){
        if(this.area()==other.area()){
            return 0;
        }
        return this.area()>other.area() ? 1 : -1;
    }
    
    public final void move(double dx, double dy){
        anchor.setX(anchor.getX()+dx);
        anchor.setY(anchor.getY()+dy);
    }
    
    public final Vector2D getAnchor(){
        return anchor;
    }
    
    public final void setAnchor(double x, double y){
        anchor.setX(x);
        anchor.setY(y);
    }
    
    public final static Comparator<GeometricObject> xComparator() {
        Comparator xComparator = new Comparator<GeometricObject>() {
            @Override
            public int compare(GeometricObject t, GeometricObject t1) {
                if (t.leftBorder() == t1.leftBorder()) {
                    return 0;
                }
                return t.leftBorder() > t1.leftBorder() ? 1 : -1;
            }
        };
        return xComparator;
    }
    
    public static Comparator<GeometricObject> yComparator(){
        Comparator yComparator = new Comparator<GeometricObject>() {
            @Override
            public int compare(GeometricObject t, GeometricObject t1) {
                if (t.bottomBorder()== t1.bottomBorder()) {
                    return 0;
                }
                return t.bottomBorder() > t1.bottomBorder() ? 1 : -1;
            }
        };
        return yComparator;
        
    }
    
}
