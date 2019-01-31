package assignment1;

import java.util.Scanner;

import nl.nils.console.Console;
import nl.nils.utilities.Utilities;

/**
 *
 * @author nils
 */
public class GroupManager {

    public static void maintainGroup(Student[] students, Console console) {
        int inputNr;
        do {
            printStudents(students, console);
            console.linePrint("Student number and new name?");
            String input = console.getInput(true);
            while (!correctStudentInput(input, false)) {
                console.linePrint(
                        "Invalid input, please use the following format: \"<student_number> <first_name> <last_name>\"\nTo quit type a negative number\nTry again");
                input = console.getInput(true);
            }
            try (Scanner scanner = new Scanner(input)) {
                inputNr = scanner.nextInt();
            }
            if (inputNr >= 0) {
                changeStudent(students, input, console);
            }
        } while (inputNr >= 0);
    }

    private static void changeStudent(Student[] students, String studentString, Console console) {
        int studentNr;
        String firstName;
        String familyName;
        try (Scanner scanner = new Scanner(studentString)) {
            studentNr = scanner.nextInt();
            firstName = scanner.next();
            familyName = scanner.next();
        }
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

    private static void printStudents(Student[] students, Console console) {
        console.linePrint("The group now contains:");
        for (Student student : students) {
            console.linePrint(student.toString());
        }
    }

    public static Student[] promptStudents(Console console) {
        Student[] students = new Student[promptGroupSize(console)];
        for (int i = 0; i < students.length; i++) {
            students[i] = promptStudent(console);
        }
        return students;
    }

    private static Student promptStudent(Console console) {
        console.linePrint("Please enter information of the next student.");
        String input = console.getInput(true);
        while (!correctStudentInput(input, true)) {
            console.linePrint(
                    "Invalid input, please use the following format: \"<student_number> <first_name> <last_name>\"\nTry again");
            input = console.getInput(true);
        }
        Student returnStudent;
        try (Scanner scanner = new Scanner(input)) {
            returnStudent = new Student(scanner.nextInt(), scanner.next(), scanner.next());
        }
        return returnStudent;
    }

    private static boolean correctStudentInput(String studentString, boolean initialization) {
        try (Scanner scanner = new Scanner(studentString)) {
            String studentNumberString;
            if (scanner.hasNext()) {
                studentNumberString = scanner.next();
            } else {
                return false;
            }
            if (!Utilities.isInteger(studentNumberString)
                    || (initialization && Integer.parseInt(studentNumberString) < 0)) {
                return false;
            }
            if (Integer.parseInt(studentNumberString) >= 0) {

                for (int i = 0; i < 2; i++) {
                    if (scanner.hasNext()) {
                        scanner.next();
                    } else {
                        return false;
                    }
                }

                if (scanner.hasNext()) {
                    return false;
                }
            }
            return true;
        }
    }

    private static int promptGroupSize(Console console) {
        console.linePrint("Please enter the group size. (Bottom of window)");
        int size = console.getIntInput(true);
        while (size <= 0) {
            console.linePrint("Please make sure the input is larger than 0, try again.");
            size = console.getIntInput(true);
        }
        return size;
    }

}
