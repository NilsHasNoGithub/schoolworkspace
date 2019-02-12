/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.desktop.QuitEvent;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import nl.nils.console.Console;
import nl.nils.utilities.Utilities;

/**
 *
 * @author nils
 */
public class Controller {

    private IOHandler iOHandler;
    private ArrayList<GeometricObject> shapes;

    public Controller() {
        shapes = new ArrayList<>();
        iOHandler = new IOHandler(this);
    }

    private void showShapes() {
        int i = 0;
        for (GeometricObject shape : shapes) {
            iOHandler.printMessage("Index: " + i++);
            iOHandler.printMessage(Utilities.seperationLine(50));
            iOHandler.printMessage(shape);
            iOHandler.printMessage(Utilities.seperationLine(50));
        }
    }

    private void addCircle(double[] args) {
        try {
            shapes.add(new Circle(args[0], args[1], args[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            iOHandler.printMessage("Failed, wrong arguments, use this command like: circle (x) (y) (radius)");
        }
    }

    private void addRectangle(double[] args) {
        try {
            shapes.add(new Rectangle(args[0], args[1], args[2], args[3]));
        } catch (ArrayIndexOutOfBoundsException e) {
            iOHandler.printMessage("Failed, wrong arguments, use this command like: rectangle (x) (y) (width) (height)");
        }
    }

    void doOption(UserOption option, double[] args) {
        switch (option) {
            case SHOW:
                showShapes();
                break;
            case CIRCLE:
                addCircle(args);
                break;
            case RECTANGLE:
                addRectangle(args);
                break;
            case MOVE:
                move(args);
                break;
            case REMOVE:
                remove(args);
                break;
            case SORT:
                if (args.length > 0) {
                    sort(args);
                } else {
                    sort();
                }
                break;
            case HELP:
                break;
            default:
                throw new AssertionError(option.name());
        }
    }

    private void move(double[] args) {
        try {
            shapes.get((int) args[0]).move(args[1], args[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            iOHandler.printMessage("Failed, wrong arguments, check if the index is correct, use this command like: move (index) (dx) (dy)");
        }
    }

    private void sort() {
        Arrays.sort(shapes.toArray(new GeometricObject[shapes.size()]));
    }

    private void sort(double[] args) {
        if (args[0] == 0) {
            Arrays.sort(shapes.toArray(new GeometricObject[shapes.size()]), GeometricObject.xComparator());
        } else if (args[0] == 1) {
            Arrays.sort(shapes.toArray(new GeometricObject[shapes.size()]), GeometricObject.yComparator());
        } else {
            iOHandler.printMessage("Failed, wrong arguments, check if correct arguments were given, use this command like: sort or: sort x/y");
        }
    }

    private void remove(double[] args) {
        try {
            shapes.remove((int) args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            iOHandler.printMessage("Failed, wrong arguments, check if the index is correct, use this command like: remove (index)");
        }
    }

}
