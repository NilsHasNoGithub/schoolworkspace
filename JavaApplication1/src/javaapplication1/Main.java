/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * @author Nils Golembiewski (s1019649)
 * @author Wadoed Baykir (s4638069)
 */

public class Main {
    public static void main(String[] args) {
        try (Console console = new Console()) {
            console.setSize(500, 300);
            console.setFeedbackIndicator(Utilities.seperationLine(50) + "\n", "\n" + Utilities.seperationLine(50));
            Student[] students=GroupUp.promptStudents(console);
            GroupUp.maintainGroup(students,console);
            console.linePrint("Bye!");
            console.waitForKeyPress("Press any key to exit...");
        }
    }
}
