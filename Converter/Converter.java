package project5;
import java.lang.Math;
/**
 * Class containing methods that allows the user to convert between number systems: binary, decimal, and hexadecimal.
 * Decimal numbers represented using int type. 
 * Binary and hexadecimal numbers represented using binary and hexadecimal strings.
 * @author Seulmin Ryu
 */
public class Converter {
	/**
	 * Method that converts from binary string to decimal number given binary string. 
	 * @param binary binary string that needs to be converted to decimal.
	 * @return decimal number equal to the binary string given in parameter. 
	 * @throws IllegalArgumentException if binary string given is invalid. 
	 */
	public static int binaryToDecimal​(String binary) throws IllegalArgumentException {		
		if (binary.isEmpty()) {
			throw new IllegalArgumentException("Please enter a valid binary.");
		}
		else if (binary.length() > 33) {
			throw new IllegalArgumentException("Please enter a valid binary.");
		}
		int answer = 0;
		if (binary.substring(0,2).equals("0b")) {
			if (binary.substring(2).isEmpty()) {
				throw new IllegalArgumentException("Please enter a valid binary.");
			}
			answer = bin2Dec(binary.substring(2)); 
		}
		else {
			throw new IllegalArgumentException("Please enter a valid binary.");	
		}
		return answer; 
	}
	
	/**
	 * Helper Method for the Wrapper method binaryToDecimal; performs the actual conversion. 
	 * @param s binary string without "0b"
	 * @return decimal number equivalent to the binary string given. 
	 */
	public static int bin2Dec(String s) {
		String test = s;
		//For loop to check if there are any numbers other than 0 and 1. 
		for (int i = 0; i < test.length(); i++) {
			if (test.charAt(i) == '0' || test.charAt(i) == '1') {
				continue;
			}
			else {
				throw new IllegalArgumentException("Please enter a valid binary.");
			}
		}
	    int length = test.length();
	    String current = test.substring(0,1);
	    String next = test.substring(1); 

	    int number = 0;
	    if (!(current.equals("0"))) {
	    		number = 1;
	    }
	    if (!next.isEmpty()) {
	    		return number * (int)Math.pow(2, length-1) + bin2Dec(next);
	    }
	    else {
	    		return number * (int)Math.pow(2, length-1); 
	    }
	}
	
	/**
	 * Method that converts from decimal number to binary string, given decimal number. 
	 * @param decimal decimal number given that needs to be converted to binary string. 
	 * @return binary string equal in value to the decimal number given. 
	 * @return null if the decimal is negative or non-existent. 
	 */
	//Helper Method 
	public static String decimalToBinary​(int decimal) {
		String test = ""+decimal; 
		if (test.isEmpty()) {
			return null;
		}
		if (decimal < 0) {
			return null; 
		}
		String answer = dec2bin(decimal);
		return "0b" + answer;
	}
	/**
	 * Helper method for wrapper method decimalToBinary; performs the actual conversion.
	 * @param decimal decimal number that needs to be converted. 
	 * @return binary string equivalent to the decimal number given. 
	 */
	public static String dec2bin(int decimal) {
		if (decimal == 0) {
			return "0";
		}
		else if (decimal == 1){
			return "1";
		}
		String s = "" + (decimal%2);
		return dec2bin(decimal/2) + s;
	}
	
	
	/**
	 * Method that converts the given hexadecimal string to binary string equal in value. 
	 * @param hex hexadecimal string that needs to be converted to a binary string. 
	 * @return binary string equal in value to the hexadecimal string given. 
	 * @return null if the hexadecimal value is not given, greater than length of 8, or doesn't start with "0x". 
	 */
	public static String hexToBinary​(String hex) {
		String answer = "";
		if (hex == "") {
			return null; 		
		}
		if (hex.length() > 10) {
			return null;
		}
		String test = "0123456789ABCDEF";
		//Check for 0x and valid values
		if (hex.substring(0,2).equals("0x")) {
			String s = hex.substring(2); 
			//For loop to check if a string has valid 
	        for (int i = 0; i < s.length(); i++) {
	        		if ((test.indexOf(s.charAt(i)) == -1)) {
	        			return null; 
	        		}
	        }
			if (!(hex.substring(2).isEmpty())) {
				answer = hex2bin(hex.substring(2));
			}
			else {
				return null; 
			}
		}
		else {
			return null; 
		}
		return "0b" + answer; 
	}
	
	/**
	 * Helper method for the wrapper method hexToBinary; performs the actual conversion given a valid hexadecimal string. 
	 * @param s hexadecimal string that needs to be converted to binary string. 
	 * @return binary string equivalent to the hexadecimal string given. 
	 */
	//Helper Method for Hex to Binary 
	public static String hex2bin(String s) {
		String test = "0123456789ABCDEF";
        String[] binValue = {"0000", "0001", "0010", "0011","0100", "0101", "0110", "0111","1000", "1001", "1010", "1011","1100", "1101", "1110", "1111"};

        int index = 0;
        if (!(s.isEmpty())) {
	        	for (int i = 0; i < test.length(); i++) {
	        		if (s.charAt(0) == test.charAt(i)) {
	        			break; 
	        		}
	        		else {
	        			index++;
	        		}
	        }
        }
        else {
        		return "";
        }
        String total = binValue[index]; 
        return total + hex2bin(s.substring(1));
	}
	
	/**
	 * Method that converts from binary string to hexadecimal string that is equivalent in value.  
	 * @param binary binary string that needs to be converted to hexadecimal string. 
	 * @return hexadecimal string that is equal in value to the binary string given. 
	 * @return null if binary string given is empty, greater than 31 characters, or doesn't start with "0b". 
	 */
	public static String binaryToHex​(String binary) {
		if (binary.isEmpty()) {
			return null;
		}
		else if (binary.length() > 33) {
			return null; 
		}
		if (binary.substring(0, 2).equals("0b")) {
			String newBinary = binary.substring(2); 
			if (newBinary.equals("")) {
				return null; 
			}
	
			for (int i = 0; i < newBinary.length(); i++) {
				if (newBinary.charAt(i) == '0' || newBinary.charAt(i) == '1') {
					continue;
				}
				else {
					return null; 
				}
			}
			String[] binValue = {"0000", "0001", "0010", "0011","0100", "0101", "0110", "0111","1000", "1001", "1010", "1011","1100", "1101", "1110", "1111"};
			String[] hexValue = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"}; 
			String hexString = ""; 
			
			int startIndex = newBinary.length()-4;
			int endIndex = newBinary.length(); 
			String test; 

			if (-4 < startIndex && startIndex < 0) {
				test = newBinary.substring(0,endIndex);
				int length = test.length();
				while ((length%4) != 0) {
					test = "0" + test; 
					length++;
				}
				for (int i = 0; i < binValue.length; i++) {
					if (test.equals(binValue[i])) {
						hexString = hexValue[i]; 
					}
				}
			}
			else if (startIndex <= -4) {
				return "";
			}
			else {
				test = newBinary.substring(startIndex, endIndex);	
				for (int i = 0; i < binValue.length; i++) {
					if (test.equals(binValue[i])) {
						hexString = hexValue[i]; 
					}
				}
			}		
			String rest = binary2hex(newBinary,startIndex-4, endIndex-4);
			return "0x" + rest + hexString;
		}
		return null;	
	}
	
	/**
	 * Helper method for the wrapper method binaryToHex; performs the actual conversion between binary and hexadecimal. 
	 * @param string string piece that is being converted. 
	 * @param start start index of the string that is being converted. 
	 * @param end end index of the string that is being converted. 
	 * @return hexadecimal string that is equivalent to the binary string given. 
	 */
	//Helper Method
	public static String binary2hex(String string, int start, int end) {
		String[] bin = {"0000", "0001", "0010", "0011","0100", "0101", "0110", "0111","1000", "1001", "1010", "1011","1100", "1101", "1110", "1111"};
		String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"}; 
		
		String total = "";
		String totalHex = ""; 
		
		if (-4 < start && start < 0) {
			total = string.substring(0,end);
			int length = total.length();
			while ((length%4) != 0) {
				total = "0" + total; 
				length++;
			}
			for (int i = 0; i < bin.length; i++) {
				if (total.equals(bin[i])) {
					totalHex = hex[i]; 
				}
			}
		}
		else if (start <= -4) {
			return "";
		}
		else {
			total = string.substring(start, end);	
			for (int i = 0; i < bin.length; i++) {
				if (total.equals(bin[i])) {
					totalHex = hex[i]; 
				}
			}
		}
		return binary2hex(string,start-4, end-4) + totalHex;
	}
	public static void main(String[] args) {
		System.out.println(binaryToDecimal​("0b1010001")); 
		System.out.println(decimalToBinary​(11234567)); 
		System.out.println(hexToBinary​("0x1")); 
	}
}
