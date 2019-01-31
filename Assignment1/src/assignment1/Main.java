package assignment1;


import static assignment1.GroupManager.maintainGroup;
import static assignment1.GroupManager.promptStudents;

import nl.nils.console.Console;

/**
 * @author Nils Golembiewski (s1019649)
 * 
 */

public class Main {

    public static void main(String[] args) {
        Console console=new Console();
        console.setSize(800, 400);
        Student[] students = promptStudents(console);
        maintainGroup(students,console);
        console.linePrint("Bye!");
        console.waitForKeyPress("Press any key to exit...");
        console.close();
    }

    
}