package nl.nils.console;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Closeable;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import nl.nils.utilities.BooleanObject;
import nl.nils.utilities.Utilities;

public class Console implements Closeable {

    /**
     * A console UI
     *
     * @author Nils Golembiewski
     */
    private volatile JFrame mainWindow;
    private volatile JTextArea printArea;
    private volatile JTextField userInputArea;
    private volatile long refreshTime = 50;
    private volatile int charLimit = 250000;
    private volatile String consoleText = "";
    private volatile boolean updateThreadRunning = true;
    private volatile String feedbackIndicatorFront = ">>";
    private volatile String feedbackIndicatorBack = "";

    /**
     * A more customizable console to use with console programs.
     *
     * @author Nils Golembiewski
     */
    public Console() {
        assert true;
        mainWindow = new JFrame();
        printArea = new JTextArea();
        userInputArea = new JTextField();
        initializeWindow();
        updateCycle();
    }

    /**
     * Initializes the window of the console
     */
    private void initializeWindow() {
        assert true;
        mainWindow.setSize(400, 400);
        mainWindow.setTitle("Console");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        printArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(printArea);
        printArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        mainWindow.add(scrollPane, BorderLayout.CENTER);
        // mainWindow.add(printArea, BorderLayout.CENTER);
        userInputArea.setEditable(false);
        userInputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        userInputArea.setPreferredSize(new Dimension(mainWindow.getSize().width, 20));
        mainWindow.add(userInputArea, BorderLayout.PAGE_END);
        mainWindow.setVisible(true);
    }

    /**
     * sets the size of the console window
     *
     * @param dimension
     */
    public void setSize(Dimension dimension) {
        assert dimension != null : "dimension should be initialized";
        assert dimension.width >= 0 : "dimesion width is less than 0";
        assert dimension.height >= 0 : "dimension height is less than 0";
        mainWindow.setSize(dimension);
    }

    /**
     * sets the size of the console window
     *
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        assert width >= 0 : "width is less than 0";
        assert height >= 0 : "height is less than 0";
        mainWindow.setSize(width, height);
    }

    /**
     * Updates the print area in the console, this happens automatically
     */
    private void updateConsole() {
        assert printArea != null : "printArea is null";
        assert consoleText != null : "consoleText is null";

        if (!printArea.getText().equals(consoleText)) {
            printArea.setText(consoleText);
            scrollToEnd();
        }

        if (consoleText.length() > charLimit) {
            consoleText = consoleText.substring(consoleText.length() - charLimit);
        }

    }

    /**
     * Sets the resizability of the console
     *
     * @param resizable When true, console window will be resizable else it will
     * not. (default: true);
     */
    public void setResizable(boolean resizable) {
        assert mainWindow != null : "mainWindow is null";
        mainWindow.setResizable(resizable);
    }

    /**
     * Sets the refresh rate at which the console window updates it contents
     * (default: 50)
     *
     * @param milliSeconds
     */
    public void setRefreshTime(long milliSeconds) {
        assert refreshTime >= 0 : "refreshTime is less than 0";
        refreshTime = milliSeconds;
    }

    /**
     * prints an object to the console
     *
     * @param object
     */
    public void print(Object object) {
        assert consoleText != null : "consoleText is null";
        addToConsole(object.toString());
    }

    /**
     * Adds a string to the console
     *
     * @param string
     */
    private void addToConsole(String string) {
        assert string != null : "string is null";
        assert consoleText != null : "consoleText is null";
        consoleText = consoleText + string;
    }

    /**
     * prints a new line
     */
    public void newLine() {
        assert consoleText != null : "consoleText is null";
        addToConsole("\n");
    }

    /**
     * gets the size of the console window
     *
     * @return the size of the console window
     */
    public Dimension getSize() {
        assert true;
        return mainWindow.getSize();
    }

    /**
     * prints an object and subsequently a newline to the console
     *
     * @param object
     */
    public void linePrint(Object object) {
        assert consoleText != null : "consoleText is null";
        addToConsole(object.toString() + "\n");
    }

    /**
     * Sets the character limit of the console print area (default: 250000)
     *
     * @param limit
     */
    public void setCharLimit(int limit) {
        assert limit >= 0 : "limit is less than 0";
        this.charLimit = limit;
    }

    /**
     * Sets the title of the console window
     *
     * @param title
     */
    public void setTitle(String title) {
        assert title != null : "title is null";
        mainWindow.setTitle(title);
    }

    /**
     * Prints a formatted string, for more info look at the javaDoc for
     * System.out.printf
     *
     * @param format
     * @param args
     */
    public void printf(String format, Object... args) {
        assert consoleText != null;
        String print = String.format(format, args);
        addToConsole(print);
    }

    /**
     * Prints a formatted string, for more info look at the javaDoc for
     * System.out.printf
     *
     * @param loc
     * @param format
     * @param args
     */
    public void printf(Locale loc, String format, Object... args) {
        assert consoleText != null;
        String print = String.format(loc, format, args);
        addToConsole(print);
    }

    /**
     * Scrolls the console to the end of the text in the print area of the
     * console
     */
    private void scrollToEnd() {
        assert printArea != null;
        if (printArea.getText().length() > 0) {
            printArea.setCaretPosition(printArea.getText().length());
        }
    }

    /**
     * Gets input from the user
     *
     * @param giveFeedBack when true, prints the value the user entered to the
     * console
     * @return input from the user as a string
     */
    public String getInput(boolean giveFeedBack) {
        assert userInputArea != null : "userInputArea is null";
        userInputArea.setEditable(true);

        if (!userInputArea.isFocusOwner()) {
            userInputArea.setText("Enter input here...");
        }

        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                userInputArea.setText("");

            }

            @Override
            public void focusLost(FocusEvent e) {
                userInputArea.setText("Enter input here...");
            }
        };

        userInputArea.addFocusListener(focusListener);

        final BooleanObject booleanObject = new BooleanObject();
        booleanObject.value = false;
        KeyListener keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    booleanObject.value = true;
                }
            }
        };
        userInputArea.addKeyListener(keyListener);
        while (!booleanObject.value) {
            Utilities.sleep(refreshTime);
        }
        String answer = userInputArea.getText();
        userInputArea.removeFocusListener(focusListener);
        userInputArea.setText("");
        userInputArea.setEditable(false);
        Utilities.sleep(refreshTime);
        userInputArea.removeKeyListener(keyListener);
        if (giveFeedBack) {
            linePrint(feedbackString(answer));
        }
        return answer;

    }

    /**
     * gets an integer from the user
     *
     * @param giveFeedBack when true, prints the value the user entered to the
     * console
     * @return returns user input
     */
    public int getIntInput(boolean giveFeedBack) {
        assert userInputArea != null;
        String answer = getInput(false);
        while (!Utilities.isInteger(answer)) {
            linePrint("Error: \"" + answer + "\" is not an integer, please provide different input...");
            answer = getInput(false);
        }
        if (giveFeedBack) {
            linePrint(feedbackString(answer));
        }
        return Integer.parseInt(answer);
    }

    /**
     * Forces the user to enter Y/y/N/n
     *
     * @param giveFeedBack when true, prints the value the user entered to the
     * console
     * @return true when the user entered Y/y otherwise false
     */
    public boolean getYesOrNoInput(boolean giveFeedBack) {
        assert userInputArea != null;
        String answer = getInput(false);
        while (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
            linePrint("Error: \"" + answer + "\" does not match Y/y/N/n. Please provide different input");
            answer = getInput(false);
        }
        if (giveFeedBack) {
            linePrint(feedbackString(answer));
        }
        return answer.toLowerCase().equals("y");
    }

    /**
     * Gets a double from the user
     *
     * @param giveFeedBack
     * @return returns user input
     */
    public double getDoubleInput(boolean giveFeedBack) {
        assert userInputArea != null;
        String answer = getInput(false);
        while (!Utilities.isDouble(answer)) {
            linePrint("Error: \"" + answer + "\" is not a double, please provide different input...");
            answer = getInput(false);
        }
        if (giveFeedBack) {
            linePrint(feedbackString(answer));
        }
        return Double.parseDouble(answer);
    }

    /**
     * Clears the entire console
     */
    public void clear() {
        assert userInputArea != null;
        consoleText = "";
        userInputArea.setText("");
    }

    /**
     * Starts a print cycle that will dump the buffer every "refreshTime"
     * milliseconds
     */
    private void updateCycle() {
        assert consoleText != null;
        assert printArea != null;
        assert userInputArea != null;

        Runnable runnable = () -> {
            while (updateThreadRunning) {
                updateConsole();
                Utilities.sleep(refreshTime);
            }
        };

        Thread updateThread = new Thread(runnable);
        updateThread.start();
    }

    /**
     * Stops the program until a key is pressed
     */
    public void waitForKeyPress() {
        final BooleanObject keyPressed = new BooleanObject();
        keyPressed.value = false;
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressed.value = true;
            }
        };
        printArea.addKeyListener(keyListener);
        userInputArea.addKeyListener(keyListener);
        while (!keyPressed.value) {
            Utilities.sleep(refreshTime);
        }
        printArea.removeKeyListener(keyListener);
        userInputArea.removeKeyListener(keyListener);
    }

    /**
     * Displays message, stops the program untill a key is pressed, deletes the
     * message.
     *
     * @param message
     */
    public void waitForKeyPress(Object message) {
        assert printArea != null : "printArea is null";
        assert userInputArea != null : "userInputArea is null";
        linePrint(message.toString());
        waitForKeyPress();
        delete(consoleText.length() - message.toString().length() - 1, consoleText.length() - 1);
    }

    /**
     * converts feedback into a feedback string according to feedbackIndicator
     *
     * @param feedback
     * @return a feedback string for the console
     */
    private String feedbackString(String feedback) {
        assert feedback != null;
        return feedbackIndicatorFront + feedback + feedbackIndicatorBack;
    }

    /**
     * Sets the string with which the user feedback is indicated default is ">>"
     *
     * @param feedbackIndicator
     */
    public void setFeedbackIndicator(String feedbackIndicator) {
        assert feedbackIndicator != null;
        this.feedbackIndicatorFront = feedbackIndicator;
    }

    /**
     * Sets the string with which the user feedback is indicated default is
     * ">>", ""
     *
     * @param feedbackIndicatorFront
     * @param feedbackIndicatorBack
     */
    public void setFeedbackIndicator(String feedbackIndicatorFront, String feedbackIndicatorBack) {
        assert feedbackIndicatorFront != null;
        this.feedbackIndicatorFront = feedbackIndicatorFront;
        this.feedbackIndicatorBack = feedbackIndicatorBack;
    }

    /**
     * Deletes characters from the console
     *
     * @param beginIndex the begin index in the text
     * @param endIndex the end index in the text
     */
    public void delete(int beginIndex, int endIndex) {
        assert beginIndex >= 0 && beginIndex < consoleText.length() : "beginIndex out of bounds";
        assert endIndex >= 0 && endIndex < consoleText.length() : "endIndex out of bounds";
        assert beginIndex <= endIndex : "beginIndex is bigger than endINdex";
        consoleText = consoleText.substring(0, beginIndex) + consoleText.substring(endIndex, consoleText.length() - 1);
    }

    /**
     * Closes the console
     */
    @Override
    public void close() {
        assert mainWindow != null;
        updateThreadRunning = false;
        mainWindow.dispose();
    }

}
