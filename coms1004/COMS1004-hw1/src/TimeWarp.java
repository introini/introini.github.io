/* Michael Introini
 * mbi2105
 * Converting Hours, Days, and Years to seconds combined.
 */

import java.util.Scanner;

public class TimeWarp {

	public static void main(String[] args) {

		// Variables
		Scanner input = new Scanner(System.in);
		int hours;
		int days;
		int years;
		
		// Get User input
		System.out.println("Please enter a number of hours");
		hours = input.nextInt();
		System.out.println("Please enter a number of days");
		days = input.nextInt();
		System.out.println("Please enter a number of years");
		years = input.nextInt();
		System.out.println("You've entered: " + hours + " hour(s), " + days + 
				" day(s), " + "and " + years + " year(s)");
		
		// Calculate
		int hoursToSeconds = hours * 60 * 60;
		int daysToSeconds = days * 24 * 60 * 60;
		int yearsToSeconds = years * 365 * 24 * 60 * 60;
		int combinedSeconds = hoursToSeconds + daysToSeconds + yearsToSeconds;
		
		// Print Result
		System.out.println("The combined number of seconds for the amount of"
				+ " time you " + "entered is: " + combinedSeconds);
		
	} // end of the main method

} // end of the class
