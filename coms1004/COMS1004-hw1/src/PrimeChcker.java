/*
 * 
 */
import java.util.Scanner;

public class PrimeChcker {
	
	public static void main(String[] args) {
		
		// Variables
		Scanner input = new Scanner(System.in);
		int isItPrime;
		int divisor = 2;
		int remainder; 
		
		// Get input from the user
		System.out.println("Please enter a positive integer, greater than 1 "
				+ "to see if its prime: ");
		isItPrime = input.nextInt();
		
		// Check the length of the input
		while (isItPrime > 10000) {
			System.out.println("Sorry, I'm not smart enough for that number.");
			System.out.println("Please enter another number to see "
					+ "if its prime: ");
			isItPrime = input.nextInt();
		} // end of the while loop
		
		remainder = isItPrime % divisor;
		
		// Catch numbers that are divisible by 2
		if (remainder == 0) {
			System.out.println("Not Prime, the factor is: " + divisor);
		} // of the if statement
		
		// Iterate through numbers past 2 
		while (remainder != 0) {
			divisor++;
			remainder = isItPrime % divisor;
			if (remainder == 0 && divisor == isItPrime) {
				System.out.println("It's Prime!");
			} else if (remainder == 0) {
				System.out.println("Not prime, and its factor is: " + divisor);
			} // end of the if statement
		}
		
	} // end of the main method

} // end of the class
