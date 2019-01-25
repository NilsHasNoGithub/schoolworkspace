package app;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(numberOfAs("AAaaSSss"));
        octal(93282);
        System.out.println();
        octalNoReee(93282);
        System.out.println();

        Band[] bands = new Band[3];
        bands[0]=new Band("abc 1 ghi");
        bands[1]=new Band("ghi 2 abc");
        bands[2]=new Band("abc 3 ghi");
        ArrayList<Band> collected = new ArrayList<Band>();
        collected.add(bands[0]);
        dumpBandTrains(bands, collected);

        int[] row= {1,2,3,4,5,2,1,1};
        System.out.println(canSplit(row));
        
    }

    private static void octal(int n) {
        if (n < 8) {
            System.out.print(n);
        } else {
            octal(n / 8);
            System.out.print(n % 8);
        }
    }

    private static int numberOfAsNoReee(String s) {
        int number = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == 'A') {
                number++;
            }
        return number;
    }

    private static void octalNoReee(int n) {
        ArrayList<Integer> print = new ArrayList<Integer>();
        while (n >= 8) {
            print.add(n % 8);
            n = n / 8;
        }
        System.out.print(n);
        for (int i = print.size() - 1; i >= 0; i--) {
            System.out.print(print.get(i));
        }
    }

    private static int numberOfAs(String string) {
        return numberOfAs(string, 0);
    }

    private static int numberOfAs(String string, int i) {
        if (i >= string.length()) {
            return 0;
        }
        if (string.charAt(i) == 'A') {
            return 1 + numberOfAs(string, i + 1);
        } else {
            return numberOfAs(string, i + 1);
        }
    }

    private static boolean match(Band first, Band second){
        return first.lastWord.equals(second.firstWord);
    }

    private static boolean contains(Band band, ArrayList<Band> collected){
        for(int i = 0; i<collected.size();i++){
            if(collected.get(i).fullName.equals(band.fullName)){
                return true;
            }
        }
        return false;
    }

    private static void dump(ArrayList<Band> collected){
        String print = "[ ";
        for(int i=0;i<collected.size();i++){
            if(i!=0){
                print = print + ", ";
            }
            print = print +collected.get(i).fullName+" ";
        }
        print=print+"]";
        System.out.println(print);
    }

    private static void dumpBandTrains(Band[] bands, ArrayList<Band> collected){
        for(int i = 0;i<bands.length;i++){
            if(match(collected.get(collected.size()-1), bands[i])&& !(contains(bands[i], collected))){
                collected.add(bands[i]);
                dumpBandTrains(bands, collected);
                collected.remove(collected.size()-1);
            }
        }
        //if(collected.size()>1)
        dump(collected);
    }

    private static boolean canSplit(int[] row){
        return canSplit(row,0,0,0);
    }

    private static boolean canSplit(int[] row, int a, int b, int i) {
        if(i==row.length){
            return a==b;
        }
        boolean first=canSplit(row,a+row[i],b,i+1);
        boolean second=canSplit(row,a,b+row[i],i+1);
        return first||second;
    }

    private static class Band {
        public String firstWord; // for example "Dave"
        public String fullName; // for example "Dave Matthews Band"
        public String lastWord; // for example "Band"

        public Band(String name){
            this.fullName=name;
            firstWord = firstWord(fullName);
            lastWord = lastWord(fullName);
        }

        private String lastWord(String name) {
            String last= "";
            for(int i=name.length()-1;i>=0;i--){
                if(name.charAt(i)==' '){
                    break;
                }
                last=last+name.charAt(i);
            }
            return reverse(last);
        }


        private String firstWord(String name) {
            String first="";
            for(int i = 0; i<name.length();i++){
                if(name.charAt(i)==' '){
                    break;
                }
                first=first+name.charAt(i);
            }
            return first;
        }
    }

    private static String reverse(String string){
        String rev="";
        for(int i=string.length()-1;i>=0;i--){
            rev=rev+string.charAt(i);
        }
        return rev;
    }

}