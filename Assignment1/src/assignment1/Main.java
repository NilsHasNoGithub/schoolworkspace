package assignment1;


import nl.nils.console.Console;
import static assignment1.GroupManager.*;

/**
 * @author Nils Golembiewski (s1019649)
 * 
 */

public class Main {

    public static void main(String[] args) {
        Console console=new Console();
        console.setSize(800, 400);
        console.setRefreshTime(50);
        Student[] students = promptStudents(console);
        maintainGroup(students,console);
        console.linePrint("Bye!");
        console.waitForKeyPress("Press any key to exit...");
        console.close();
    }

    
}