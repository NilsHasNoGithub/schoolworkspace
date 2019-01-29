package assignment1;
import java.util.Scanner;

import nl.nils.console.Console;
import nl.nils.utilities.Utilities;

/**
 * @author Nils Golembiewski (s1019649)
 * 
 */

public class Main {
    private static final Console console=new Console();
    public static void main(String[] args) {
        console.setSize(600,400);
        Student[] students=promptStudents();
        console.linePrint("The group now contains:");
        printStudents(students);
        console.waitForKeyPress("Press any key to exit...");
        console.close();
    }

    private static void printStudents(Student[] students) {
        for(int i=0;i<students.length;i++){
            console.linePrint(students[i].toString());
        }
    }

    public static Student[] promptStudents() {
        Student[] students = new Student[promptGroupSize()];
        for(int i = 0; i<students.length;i++){
            students[i]=promptStudent();
        }
        return students;
    }

    private static Student promptStudent() {
        console.linePrint("Please enter information of the next student.");
        String input = console.getInput(true);
        while(!correctStudentInput(input)){
            console.linePrint("Invalid input, please use the following format: \"<student_number> <first_name> <last_name>\"\nTry again");
            input=console.getInput(true);
        }
        Scanner scanner=new Scanner(input);
        Student returnStudent=new Student(scanner.nextInt(), scanner.next(), scanner.next());
        scanner.close();
        return returnStudent;
    }

    private static boolean correctStudentInput(String studentString) {
        Scanner scanner = new Scanner(studentString);
        if(!Utilities.isInteger(scanner.next())){
            scanner.close();
            return false;
        }
        scanner.next();
        scanner.next();
        if(scanner.hasNext()){
            scanner.close();
            return false;
        }
        scanner.close();
        return true;
    }

    private static int promptGroupSize() {
        console.linePrint("Please enter the group size. (Bottom of window)");
        int size=console.getIntInput(true);
        while(size<=0){
            console.linePrint("Please make sure the input is larger than 0, try again.");
            size=console.getIntInput(true);
        }
        return size;
    }
}