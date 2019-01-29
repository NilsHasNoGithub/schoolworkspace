package assignment1;

import java.util.Scanner;

import nl.nils.console.Console;
import nl.nils.utilities.Utilities;

/**
 * @author Nils Golembiewski (s1019649)
 * 
 */

public class Main {
    private static final Console console = new Console();

    public static void main(String[] args) {
        console.setSize(800, 400);
        console.setRefreshTime(50);
        Student[] students = promptStudents();
        printStudents(students);
        maintainGroup(students);
        console.linePrint("Bye!");
        console.waitForKeyPress("Press any key to exit...");
        console.close();
    }

    private static void maintainGroup(Student[] students) {
        int inputNr;
        do {
            console.linePrint("Student number and new name?");
            String input = console.getInput(true);
            while (!correctStudentInput(input, false)) {
                console.linePrint(
                        "Invalid input, please use the following format: \"<student_number> <first_name> <last_name>\"\nTo quit type a negative number\nTry again");
                input = console.getInput(true);
            }
            Scanner scanner = new Scanner(input);
            inputNr = scanner.nextInt();
            scanner.close();
            if (inputNr >= 0) {
                changeStudent(students, input);
                printStudents(students);
            }
        } while (inputNr >= 0);
    }

    private static void changeStudent(Student[] students, String studentString) {
        Scanner scanner = new Scanner(studentString);
        int studentNr = scanner.nextInt();
        String firstName = scanner.next();
        String familyName = scanner.next();
        scanner.close();
        int index = findStudent(students, studentNr);
        if (index == students.length) {
            console.linePrint("Student s" + studentNr + " not found, no changes were made.");
        } else {
            students[index] = new Student(studentNr, firstName, familyName);
            console.linePrint("Name of student s" + studentNr + " has been updated.");
        }
    }

    private static int findStudent(Student[] students, int studentNr) {
        for (int i = 0; i < students.length; i++) {
            if (studentNr == students[i].STUDENT_NR) {
                return i;
            }
        }
        return students.length;
    }

    private static void printStudents(Student[] students) {
        console.linePrint("The group now contains:");
        for (int i = 0; i < students.length; i++) {
            console.linePrint(students[i].toString());
        }
    }

    public static Student[] promptStudents() {
        Student[] students = new Student[promptGroupSize()];
        for (int i = 0; i < students.length; i++) {
            students[i] = promptStudent();
        }
        return students;
    }

    private static Student promptStudent() {
        console.linePrint("Please enter information of the next student.");
        String input = console.getInput(true);
        while (!correctStudentInput(input, true)) {
            console.linePrint(
                    "Invalid input, please use the following format: \"<student_number> <first_name> <last_name>\"\nTry again");
            input = console.getInput(true);
        }
        Scanner scanner = new Scanner(input);
        Student returnStudent = new Student(scanner.nextInt(), scanner.next(), scanner.next());
        scanner.close();
        return returnStudent;
    }

    private static boolean correctStudentInput(String studentString, boolean initialization) {
        Scanner scanner = new Scanner(studentString);
        String studentNumberString = scanner.next();
        if (!Utilities.isInteger(studentNumberString)
                || (initialization && Integer.parseInt(studentNumberString) < 0)) {
            scanner.close();
            return false;
        }
        if (Integer.parseInt(studentNumberString) >= 0) {

            for (int i = 0; i < 2; i++) {
                if (scanner.hasNext()) {
                    scanner.next();
                } else {
                    scanner.close();
                    return false;
                }
            }

            if (scanner.hasNext()) {
                scanner.close();
                return false;
            }
        }

        scanner.close();
        return true;
    }

    private static int promptGroupSize() {
        console.linePrint("Please enter the group size. (Bottom of window)");
        int size = console.getIntInput(true);
        while (size <= 0) {
            console.linePrint("Please make sure the input is larger than 0, try again.");
            size = console.getIntInput(true);
        }
        return size;
    }
}