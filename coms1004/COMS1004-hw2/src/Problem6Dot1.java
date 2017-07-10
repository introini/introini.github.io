/* Michael Introini
 * mbi2105
 * This class performs many different computations using loops on numbers.
 */

import java.util.Scanner;

public class Problem6Dot1 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		// 6.1 //
		// A - The sum of all even numbers between 2 and 100 (inclusive).
		System.out.println("Problem 6.1 - A ");
		int sumA = 0;
		for (int i = 2; i <= 100; i++){
			if (i % 2 == 0) {
				sumA = sumA + i;
			}
		}
		System.out.println(sumA);
		
		
		// B - The sum of all squares between 1 and 100 (inclusive).
		System.out.println("\nProblem 6.1 - B");
		double sumB = 0;
		for (int i = 1; i <= 100; i++){
			sumB = Math.pow(i,2);
		};
		System.out.println(sumB);
		
		
		// C - All powers of 2 from 2^0 up to 2*20.
		System.out.println("\nProblem 6.1 - C");
		for (int i = 0; i <= 20; i++) {
			System.out.println(Math.pow(2, i));
		}
		
		
		// D - The sum of all odd numbers between a and b (inclusive), 
		// where a and b are inputs.
		System.out.println("\nProblem 6.1 - D");
		int a;
		int b;
		int sumD = 0;
		
		System.out.println("Enter a number: ");
		a = input.nextInt();
		
		System.out.println("Enter another number: ");
		b = input.nextInt();
		
		for (int i = a; i <= b; i++) {
			if (!(i % 2 == 0))
				sumD = sumD + i;
		}
		
		System.out.println("The sum of all odd numbers between " + a +
				" and " + b + " is: " + sumD);
		
		
		// E - The sum of all odd digits of an input.
		System.out.println("\nProblem 6.1 - E");
		String number;
		int digit;
		int sumE = 0;
		System.out.println("Enter a number: ");
		number = input.next();
		
		for (int i = 0; i < number.length(); i++) {
			digit = Integer.parseInt(number.substring(i,i + 1));
			if (!(digit % 2 == 0)) {
				sumE = sumE + digit;
			}
		}
		
		System.out.println("The sum of all odd digits from the number "
				+ "that you entered is: " + sumE);
	}
}