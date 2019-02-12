/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.Closeable;
import java.lang.invoke.MethodHandles;
import nl.nils.console.Console;

/**
 *
 * @author nils
 */
public class IOHandler implements Closeable {

    private Console console;
    private Controller controller;

    public IOHandler(Controller controller) {
        this.controller = controller;
        console = new Console();
        console.setSize(800, 400);
        console.linePrint("Welcome, please enter a command or type help to view a list of caommands.");
        loop();
    }

    private void loop() {
        boolean quit = false;
        boolean correctInput = false;
        do {
            UserOptionWithArgs optionWithArgs = getUserOption();
            quit = optionWithArgs.option == UserOption.QUIT;
            if (!quit) {
                controller.doOption(optionWithArgs.option, optionWithArgs.args);
            }
        } while (!quit);
        console.waitForKeyPress("Alright, press any key to exit...");
        close();
    }

    public void printMessage(Object message) {
        console.linePrint(message);
    }

    @Override
    public void close() {
        console.close();
    }

    private UserOptionWithArgs getUserOption() {
        boolean correctInput;
        String option=null;
        double[] dArgs=null;
        do {
            try {
                correctInput = true;
                String[] sArgs = console.getInput(true).split(" ");
                option = sArgs[0];
                dArgs = getDoubleArgs(option, sArgs);
                correctInput=UserOption.isOption(option);
            } catch (Exception e) {
                e.printStackTrace();
                console.linePrint("Error, something went wrong, please try different input.");
                correctInput = false;
            }
        } while (!correctInput);
        return new UserOptionWithArgs(UserOption.toOption(option), dArgs);
    }

    private double[] getDoubleArgs(String option, String[] sArgs) {
        double[] dArgs;
        if (option.toLowerCase().equals(UserOption.SORT.name().toLowerCase()) && sArgs.length >= 1) {
            dArgs = new double[1];
            if (sArgs[1].toLowerCase().equals("x")) {
                dArgs[0] = 0;
            } else if (sArgs[1].toLowerCase().equals("y")) {
                dArgs[0] = 1;
            } else {
                dArgs[0] = -1;
            }
            return dArgs;
        }
        dArgs = new double[sArgs.length - 1];
        for (int i = 1; i < sArgs.length; i++) {
            dArgs[i - 1] = Double.parseDouble(sArgs[i]);
        }
        return dArgs;
    }

    private class UserOptionWithArgs {
        final double[] args;
        final UserOption option;

        UserOptionWithArgs(UserOption option, double[] args) {
            this.option=option;
            this.args=args;
        }

    }

}
