/* Michael Introini
 * mbi2105
 * This class checks 12-digit credit card number to ensure they are not fake.
 * The following checks are made:
 * -1- The first digit must be a 4.
 * -2- The fourth digit must be one greater than the fifth digit.
 * -3- The product of the first, fifth, and ninth digits must be 24.
 * -4- The sum of all digits must be evenly divisible by 4.
 * -5- The sum of the first four digits must be one less than the sum of the 
 *     last four digits
 * -6- If you treat the first two digits as a two-digit number, and the seventh 
 *     and eight digits as a two digit number, their sum must be 100.
 */

public class CreditCard {

	// INstance variables
	private String ccNumber;
	private boolean valid;
	private int errorCode;
	
	//Constructor for the class, assumes card is valid until check disproves
	public CreditCard(String num) {
		ccNumber = num;
		valid = true;
		errorCode = 0;
	}
	
	// Checks that the first digit of the card is a 4.
	private void check1() {
		char ch = ccNumber.charAt(0);
		String firstDigit =  String.valueOf(ch);
		if (!(firstDigit.equals("4"))) {
			valid = false;
			errorCode = 1;
		}
	}
	
	// Checks that the fourth digit is 1 greater than the fifth digit. 
	private void check2() {
		int fourthDigit = Integer.parseInt(ccNumber.substring(3,4));
		int fifthDigit = Integer.parseInt(ccNumber.substring(4,5));

		if (!(fourthDigit == fifthDigit + 1)) {
			valid = false;
			errorCode = 2;
		}
	}
	
	// Checks that the product of the First, Fifth, and Ninth digits equals 24
	private void check3() {
		int firstDigit = Integer.parseInt(ccNumber.substring(0,1));
		int fifthDigit = Integer.parseInt(ccNumber.substring(4,5));
		int ninthDigit = Integer.parseInt(ccNumber.substring(8,9));
		
		if (!(firstDigit * fifthDigit * ninthDigit == 24)) {
			valid = false;
			errorCode = 3;
		}
	}
	
	// Checks that the sum of all the digits is evenly divisible by 4
	private void check4() {
		int sum = 0;
		int digit; 
		
		for (int i = 0; i < ccNumber.length(); i++) {
			digit = Integer.parseInt(ccNumber.substring(i, i + 1));
			sum = sum + digit;
		}
		
		if (sum % 4 != 0) {
			valid = false;
			errorCode = 4;
		}
	}
	
	// Checks that the sum of the first four digits is 1 less than the sum
	// of the last four digits
	private void check5() {
		int firstFourSum = 0;
		int	lastFourSum = 0;
		int digit; 
		String firstFour = ccNumber.substring(0,4);
		String lastFour = ccNumber.substring(8,12);
		
		for (int i = 0; i < firstFour.length(); i++) {
			digit = Integer.parseInt(firstFour.substring(i, i + 1));
			firstFourSum = firstFourSum + digit;
		}
		for (int i = 0; i < lastFour.length(); i++) {
			digit = Integer.parseInt(lastFour.substring(i, i + 1));
			lastFourSum = lastFourSum + digit;
		}
		
		if (!(firstFourSum == lastFourSum - 1)) {
			valid = false;
			errorCode = 5;
		}
	}
	
	// Treats the first two numbers as a two-digit number, and the seventh
	// and eighth numbers as a two-digit number and checks that the sum is
	// equal to 100.
	private void check6() {
		int firstTwoDigits = Integer.parseInt(ccNumber.substring(0,2));
		int secondTwoDigits = Integer.parseInt(ccNumber.substring(6,8));
		
		if (!(firstTwoDigits + secondTwoDigits == 100)) {
			valid = false;
			errorCode = 6;
		}
	}
	
	// Goes through each check in order from 1 - 6 to validate the card number
	public void check() {
		if (valid == true) {
			check1();
		}
		if (valid == true) {
			check2();
		} 
		if (valid == true) {
			check3();
		} 
		if (valid == true) {
			check4();
		} 
		if (valid == true) {
			check5();
		} 
		if (valid == true) {
			check6();
		}
		
	}
	
	// Accessor method to get a error code that was generated, if any.
	public int getErrorCode() {
		return errorCode;
	}
	
	// Accessor method that returns true or false depending on the validity of
	// the credit card number.
	public boolean isValid() {
		return valid;
	}
	
}
