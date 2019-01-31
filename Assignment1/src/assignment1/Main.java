package assignment1;

import static assignment1.GroupManager.maintainGroup;
import static assignment1.GroupManager.promptStudents;

import nl.nils.console.Console;
import nl.nils.utilities.Utilities;

/**
 * @author Nils Golembiewski (s1019649)
 * 
 */

public class Main {

    public static void main(String[] args) {
        try (Console console = new Console()) {
            console.setSize(800, 400);
            console.setFeedbackIndicator(Utilities.seperationLine(50) + "\n", "\n" + Utilities.seperationLine(50));
            Student[] students = promptStudents(console);
            maintainGroup(students, console);
            console.linePrint("Bye!");
            console.waitForKeyPress("Press any key to exit...");
        }
    }

}