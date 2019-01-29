package nl.nils.utilities;

public class MyString {
    public String value;

    /**
     * Custom string object with some extra utilities
     * 
     * @param string
     */
    public MyString(String string) {
        this.value = string;
    }

    /**
     * Custom string object with some extra utilities
     * 
     * @param charArray character array with the contents of the new string.
     */
    public MyString(char[] charArray) {
        assert charArray!=null;
        assert value!=null;
        this.value = "";
        for (int i = 0; i < charArray.length; i++) {
            this.value = this.value + charArray[i];
        }
    }

    /**
     * Counts the amount of times that character c appears in the string
     * 
     * @param c
     * @return the amount of character c in this
     */
    public int amountOf(char c) {
        assert value != null;
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
    /**
     * Counts the amount of times that String string appears in the string
     * 
     * @param string
     * @return the amount of String string in this
     */
    public int amountOf(String string){
        int count=0;
        for(int i = 0;i<=value.length()-string.length();i++){
            for(int j=0;j<string.length()&&value.charAt(i+j)==string.charAt(j);j++){
                if(j==string.length()-1){
                    count++;
                }
            }
        }
        return count;
    }

}