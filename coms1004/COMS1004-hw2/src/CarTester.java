/* Michael Introini
 * mbi2105
 * This is a tester for the Car class.
 */
public class CarTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Car myCar = new Car(50); // 50 miles per gallon
		
		myCar.addGas(20); // Tank 20 gallons
		myCar.drive(100); // Drive 100 miles
		
		double gasLeft = myCar.getGasInTank(); // Get gas remaining in tank
		
		// Print out how many gallons left
		System.out.println("You have " + gasLeft + " gallons of gas left in your tank.");
	}

}
