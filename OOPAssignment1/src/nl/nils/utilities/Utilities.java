package nl.nils.utilities;

import java.awt.Dimension;
import java.util.ArrayList;

public class Utilities {
    /**
     * returns whether a string is an integer
     * 
     * @param string
     * @return true if string is an integer, otherwhise false
     */
    public static boolean isInteger(String string) {
        assert true;
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Sleeps the thread for millis milliseconds
     * @param millis
     */
    public static void sleep(long millis){
        assert millis >=0;
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){}
    }

    /**
     * Returns whether a string is a double
     * 
     * @param string
     * @return true if string is a double, otherwise false
     */
    public static boolean isDouble(String string) {
        assert true;
        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * converts an array to an arrayList
     * 
     * @param array
     * @return the array list
     */
    public static <T> ArrayList<T> toArrayList(T[] array) {
        assert array != null : "array is null";
        ArrayList<T> arrayList = new ArrayList<T>();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    /**
     * creates a copy of an array list
     * 
     * @param arrayList
     * @return a copy of the arrayList
     */
    public static <T> ArrayList<T> copyArrayList(ArrayList<T> arrayList) {
        assert arrayList != null : "arrayList is null";
        ArrayList<T> copiedArrayList = new ArrayList<T>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            copiedArrayList.add(arrayList.get(i));
        }
        return copiedArrayList;
    }

    /**
     * removes a/all values in an arraylist that match value
     * 
     * @param options
     * @param value
     * @param allValues when true deletes all matching values, otherwise deletes the
     *                  first one
     */
    public static void removeByValue(ArrayList<Integer> options, int value, boolean allValues) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i) == value) {
                options.remove(i);
                if (allValues) {
                    i--;
                } else {
                    return;
                }
            }
        }
    }

    /**
     * compares items in an array and returns the best one
     * 
     * @param array
     * @return the best item in the array
     */
    public static <T extends Comparable<T>> T bestItemOfArray(T[] array) {
        assert array != null : "array should be initialized";
        assert array.length >= 1 : "array should contain at least 1 item";
        int bestIndex = 0;
        for (int i = 1; i < array.length; i++) {
            assert array[i] != null : "array at index " + i + " should be initialized";
            if (array[i].compareTo(array[bestIndex]) == 1) {
                bestIndex = i;
            }
        }
        return array[bestIndex];
    }

    
    /**
     * returns a useful seperation line
     * @return a useful speratioin line
     */
    public static String seperationLine(){
        assert true;
        return seperationLine(50);
    }
    /**
     * returns a useful seperation line
     * @param length the length of the seperation line
     * @return a useful seperation line
     */
    public static String seperationLine(int length){
        assert true;
        String string = "";
        for(int i =0;i<length;i++){
            string=string+"-";
        }
        return string;
    }
    /**
     * Checks whether an object is an an array list
     * @param arrayList
     * @param type
     * @return true if the object is in the array list, otherwise false
     */
    public static <T> boolean inArrayList(ArrayList<T> arrayList,T type){
        assert type!=null:"type is null";
        assert arrayList!=null:"arrayList is null";
        for(int i = 0;i<arrayList.size();i++){
            assert arrayList.get(i)!=null:"type in arrayList is null";
            if(type.equals(arrayList.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * compares items in an array list and returns the best one
     * 
     * @param arrayList
     * @return the best item in the array list
     */
    public static <T extends Comparable<T>> T bestItemOfArray(ArrayList<T> arrayList) {
        assert arrayList != null : "array should be initialized";
        assert arrayList.size() >= 1 : "array should contain at least 1 item";
        int bestIndex = 0;
        for (int i = 1; i < arrayList.size(); i++) {
            assert arrayList.get(i) != null : "array at index " + i + " should be initialized";
            if (arrayList.get(i).compareTo(arrayList.get(bestIndex)) == 1) {
                bestIndex = i;
            }
        }
        return arrayList.get(bestIndex);
    }

    /**
     * returns whether a character is an integer
     * 
     * @param character
     * @return true when the character is an integer, otherwise false
     */
    public static boolean isInteger(Character character) {
        assert character != null : "character is null";
        return isInteger(character.toString());
    }

    /**
     * returns whether a character is a double
     * 
     * @param character
     * @return true when the character is a double, otherwise false
     */
    public static int toInt(Character character) {
        assert character != null : "character is null";
        return Integer.parseInt(character.toString());
    }

    /**
     * returns the sum of an integer array list
     * 
     * @param arrayList
     * @return the sum of an integer array list
     */
    public static int sum(ArrayList<Integer> arrayList) {
        assert arrayList != null : "arrayList is null";
        int sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            assert arrayList.get(i)!=null:"item from arrayList is null";
            sum += arrayList.get(i);
        }
        return sum;
    }
    /**
     * Copies values from a 2d array to another one
     * @param array2d
     * @param array2dCopied
     */
    public static void copy2dArray(int[][] array2d, int[][] array2dCopied) {
        assert array2d!=null:"array2d is null";
        assert array2dCopied!=null:"array2dCopied is null";
        assert sameLengths(array2d, array2dCopied);
        for (int i = 0; i < array2d.length; i++) {
            for (int j = 0; j < array2d[i].length; j++) {
                array2dCopied[i][j] = array2d[i][j];
            }
        }
    }
    /**
     * Checks whether 2 2d arrays have same lengths for all arrays
     * @param array1
     * @param array2
     * @return true when all array lengths are the same, otherwise false
     */
	public static boolean sameLengths(int[][] array1, int[][] array2) {
        assert array1!=null:"array1 is null";
        assert array2!=null:"array2 is null";
        if(array1.length!=array2.length){
            return false;
        }

        for(int i = 0; i < array1.length;i++){
            if(array1[i].length!=array2[i].length){
                return false;
            }
        }
		return true;
	}
    /**
     * returns the sum of a double array list
     * 
     * @param arrayList
     * @return the sum of a double array list
     */
	public static double sumOfDoubleArray(ArrayList<Double> arrayList) {
		assert arrayList != null : "arrayList is null";
        double sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            assert arrayList.get(i)!=null:"item from arrayList is null";
            sum += arrayList.get(i);
        }
        return sum;
	}
    /**
     * returns the dimension of a 2d array if all arrays in the first array are equal
     * @param array2d
     * @return the dimension of a 2d array if all arrays in the first array are equal, otherwise returns null
     */
	public static Dimension dimensionOf2Darray(int[][] array2d) {
        assert array2d!=null:"board is null";
        assert array2d.length!=0:"board length is 0";
        int height=array2d.length;
        int width =array2d[0].length;
        for(int col=0;col<height;col++){
            if(array2d[col].length!=width){
                return null;
            }
        }
        return new Dimension(width,height);
	}
}