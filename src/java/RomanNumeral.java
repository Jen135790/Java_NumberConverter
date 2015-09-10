/*
 *  [File]
 *  RomanNumeral.java
 *  
 *  [Tasks]
 *  - Validate input to make sure a ROMAN NUMERAL was entered
 *  - Handle Hex and Decimal Conversions
 */
package numberconverter;

public class RomanNumeral {
    private String romInput = "";
    private int decVal = 0;
    
    public RomanNumeral(){
        romInput = "";
    }
    
    public RomanNumeral(String input){
        romInput = input.toUpperCase();
    }
    
    public boolean isValidRom(){
        //Uses regular expressions to validate roman numeral:
        boolean valid = romInput.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        
        return valid;
    }
    
    public String convertToDec(){
        for(int i = 0; i < romInput.length(); i++){
		if(i == 0){ //Checks the first element:
			switch(romInput.charAt(i)){
			case 'I':
				decVal += 1;
				break;
			case 'V':
				decVal += 5;
				break;
			case 'X':
				decVal += 10;
				break;
			case 'L':
				decVal += 50;
				break;
			case 'C':
				decVal += 100;
				break;
			case 'D':
				decVal += 500;
				break;
			case 'M':
				decVal += 1000;
				break;
			}
		}
		else{
                    //All other elements will be checked:
                    //If the index before the current index, double the amount of the previous index will be
                    //subtracted before the amount of the current index will be added!
			if(romInput.charAt(i) == 'I'){
				decVal += 1;
			}
			else if(romInput.charAt(i) == 'V'){
				if(romInput.charAt(i - 1) == 'I'){
					decVal -= 2;
				}
				decVal += 5;
			}
			else if(romInput.charAt(i) == 'X'){
				if(romInput.charAt(i - 1) == 'I'){
					decVal -= 2;
				}
				decVal += 10;
			}
			else if(romInput.charAt(i) == 'L'){
				if(romInput.charAt(i - 1) == 'X'){
					decVal -= 20;
				}
				decVal += 50;
			}
			else if(romInput.charAt(i) == 'C'){
				if(romInput.charAt(i - 1) == 'X'){
					decVal -= 20;
				}
				decVal += 100;
			}
			else if(romInput.charAt(i) == 'D'){
				if(romInput.charAt(i - 1) == 'C'){
					decVal -= 200;
				}
				decVal += 500;
			}
			else if(romInput.charAt(i) == 'M'){
				if(romInput.charAt(i - 1) == 'C'){
					decVal -= 200;
				}
				decVal += 1000;
			}
		}
	}
        
        return String.valueOf(decVal);
    }
    
    public String convertToHex(){
        //Easier to convert to decimal first:
        decVal = Integer.parseInt(convertToDec());
        
        //Then convert decimal to hex:
        char[] hexChars = {'0', '1', '2', '3', '4','5', '6', '7', '8','9','A','B','C','D','E','F'};
        long remainder;
        StringBuilder conNum = new StringBuilder();
        
        do{
            remainder = decVal % 16;
            decVal = decVal / 16;
            conNum.append(hexChars[(int)remainder]);
            
        }while(decVal > 0);
        
        return conNum.reverse().toString();
    }
}
