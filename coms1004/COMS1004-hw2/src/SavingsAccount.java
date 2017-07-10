/* Michael Introini
 * mbi2105
 * Creating a Savings account class that takes an initial balance and interest
 * This class also contains methods to deposit, return a balance, and add
 * interest.
 */

public class SavingsAccount {
	
	// Variables that are only used in this class 
	private double balance;
	private double interest;
	
	// Constructs a Savings account with no money in it.
	public SavingsAccount(){
		balance = 0;
	}
	
	// Contructs a Savings account with an initial balance and initial interest
	public SavingsAccount(double initialBalance, double initialInterest) {
		balance = initialBalance; 
		interest = initialInterest;
	}
	
	// Method to add a deposit to the balance.
	public void deposit(double amount){
		balance = balance + amount;
	}
	
	// Method to return the current balance
	public double getBalance() {
		return balance;
	}
	
	/* Method to add interest to the balance based on the percentage specified 
	*  when declaring the initial instance of SavingsAccount
	*/  
	public void addInterest() {
		double currentBalance = this.getBalance();
		double calculatedInterest = currentBalance * interest;
		balance = balance + calculatedInterest;
	}
	

}
