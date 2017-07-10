/* Michael Introini
 * mbi2105
 * Car Class
 */

public class Car {
	
	// Global variables for the class
	private double efficiency;
	private double fuelInTank;
	
	// Constructor that sets the initial values of the class
	public Car(double initialEfficiency) {
		fuelInTank = 0;
		efficiency = initialEfficiency;
	}
	
	// Method to check how much gas is in the tank
	public double getGasInTank() {
		return fuelInTank;
	}
	
	// Method to add gas to the tank
	public void addGas(double amountOfGas) {
		fuelInTank = fuelInTank + amountOfGas;
	}
	
	// Method to simulate driving
	public void drive(int milesToDrive) {
		/* Initialize a variable to keep track of how many miles have been 
		*  driven.
		*/
		double milesDriven = 0;
		
		// Drive until all miles were driven
		while (milesDriven < milesToDrive) {
			// Every time the remainder is 0 subtract 1 gallon from the tank
			if (milesDriven % efficiency == 0) {
				fuelInTank--;
			}
			// Increase miles driven by 1
			milesDriven++;
		}
	}
}
