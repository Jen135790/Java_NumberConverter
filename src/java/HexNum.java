/*
 *  [File]
 *  HexNum.java
 *  
 *  [Tasks]
 *  - Validate input to make sure a HEXADECIMAL was entered
 *  - Handle Decimal and Roman Numeral Conversions
 */
package numberconverter;

public class HexNum {
    private String hexInput = "";
    private long decVal = 0;
    private String romVal = "";
    
    public HexNum(){
        hexInput = "";
    }
    
    public HexNum(String input){
        hexInput = input;
    }
    
    public boolean isValidHex(){
        boolean valid = true;
        
        try{
            long i = Long.parseLong(hexInput,16);   //Just to validate hex
            
            if(i > 4294967295L || i <= 0){
                valid = false;
            }
        }
        catch(NumberFormatException e){
            valid = false;
        }
        
        return valid;
    }
    
    public String convertToDec(){
        //Use the Long object to automatically convert hex to decimal:
        decVal = Long.parseLong(hexInput, 16);
        
        return String.valueOf(decVal);
    }
    
    public String convertToRoman(){
        long[] dec = {1000, 900, 500, 400, 100, 90, 50, 40, 10,9, 5, 4, 1};
        String[] romChars = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        romVal = "";
        
        //Easier to convert to decimal first then convert to roman
        decVal = Long.parseLong(hexInput, 16);
        int origVal = (int)decVal;
        
        for(int i = 0; i < 13; i++){
            while(origVal >= dec[i]){
                origVal -= dec[i];
                romVal += romChars[i];
            }
        }
        return romVal;
    }
}
