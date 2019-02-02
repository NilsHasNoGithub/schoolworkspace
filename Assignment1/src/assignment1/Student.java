package assignment1;

import java.util.Scanner;

/**
 * @author Nils Golembiewski (s1019649)
 * 
 */

 public class Student{
    private String firstName;
    private String familyName;
    public final int STUDENT_NR;

    public Student(int studentNr, String firstName,String familyName){
        this.firstName=firstName;
        this.familyName=familyName;
        this.STUDENT_NR=studentNr;
    }

    public void setName(String name){
        try (Scanner scanner = new Scanner(name)) {
            this.firstName=scanner.next();
            this.familyName=scanner.next();
        }
    }

    @Override
    public String toString(){
        return this.firstName + " " + this.familyName + ", s" + this.STUDENT_NR;
    }
 }