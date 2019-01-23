package app;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
       System.out.println(numberOfAs("AAaaSSss"));
       octal(93282);
       System.out.println();
       octalNoReee(93282);
    }

    private static void octal(int n) {
        if(n<8){
            System.out.print(n);
        }else{
            octal(n/8);
            System.out.print(n%8);
        }
    }

    private static void octalNoReee(int n){
        while (n>=8){
            System.out.print(n%8);
            n=n/8;
        }
        System.out.print(n);

        /*ArrayList<Integer> print = new ArrayList<Integer>();
        while(n>=8){
            print.add(n%8);
            n=n/8;
        }
        System.out.print(n);
        for(int i=print.size()-1;i>=0;i--){
            System.out.print(print.get(i));
        }*/
    }

    private static int numberOfAs(String string) {
        return numberOfAs(string, 0);
    }

    private static int numberOfAs(String string, int i) {
        if(i>=string.length()){
            return 0;
        }
        if(string.charAt(i)=='A'){
            return 1+numberOfAs(string, i+1);
        }else{
            return numberOfAs(string, i+1);
        }
    }
}