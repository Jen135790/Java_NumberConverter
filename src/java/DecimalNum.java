/*
 *  [File]
 *  DecimalNum.java
 *  
 *  [Tasks]
 *  - Validate input to make sure a NUMBER was entered
 *  - Handle Hex and Roman Numeral Conversions
 */
package numberconverter;

public class DecimalNum {
    private String input;
    private String convertedVal = "";
    private long origNum = 0;
    public DecimalNum(){
        input = "";
    }
    
    public DecimalNum(String in){
        input = in;
    }
    
    //Check if user entered an integer:
    public boolean isValidInput(int conType){
        boolean valid = true;
        try{
            long i = Long.parseLong(input);
            if((i <= 4294967295L && i > 0) && conType == 0)     //Converting to Hex
            {
                origNum = i;
                valid = true;
            }
            else if((i < 5000 && i > 0) && conType == 1){
                origNum = i;
                valid = true;
            }
            else{
                valid = false;
            }
        }
        catch(NumberFormatException e){
            valid = false;
        }
        return valid;
    }
    
    public String convertToHex(){
        char[] hexChars = {'0', '1', '2', '3', '4','5', '6', '7', '8','9','A','B','C','D','E','F'};
        long remainder;
        StringBuilder conNum = new StringBuilder();
        
        //Take remainder to determine which hex value then divide the origNum by 16. 
        //Do this until origNum is 0.
        do{
            remainder = origNum % 16;
            origNum = origNum / 16;
            conNum.append(hexChars[(int)remainder]);
            
        }while(origNum > 0);
        
        
        //The finished conNum will be backwards so it needs to be reversed!
        convertedVal = conNum.reverse().toString();
        return convertedVal;
    }
    
    public String convertToRoman(){
        long[] dec = {1000, 900, 500, 400, 100, 90, 50, 40, 10,9, 5, 4, 1};
        String[] romChars = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        convertedVal = "";
        int origVal = (int)origNum;
        
        for(int i = 0; i < 13; i++){
            //While the origVal is greater than the value dec[i], that value will be 
            //subtracted from origVal and the converted value will append the letters in that
            //index
            while(origVal >= dec[i]){
                origVal -= dec[i];
                convertedVal += romChars[i];
            }
        }
        
        return convertedVal;
    }
}
