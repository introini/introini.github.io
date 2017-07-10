/* Michael Introini
 * mbi2105
 * A class to test the SavingsAccount Class
 */
public class SavingsAccountTester {
	/*
	 * Tests the methods of the SavingsAccount class
	 */
	public static void main(String[] args) {
		/* Implement a new instance of the Savings account class called "acct"
		 * initializes the instance with $1000 and 10% interest 
		 */
		SavingsAccount acct = new SavingsAccount(1000.00, 0.10);
		
		// Print out initial balance 
		System.out.println("Initial Balance: " + acct.getBalance());
		
		// Calculate interest and add it to the balance
		acct.addInterest();
		
		/* Print out the expected and final balance after interest has been 
		* added
		*/
		System.out.println("Expected Balance: 1100" );
		System.out.println("New Balance after interest " + acct.getBalance());
	}

}
