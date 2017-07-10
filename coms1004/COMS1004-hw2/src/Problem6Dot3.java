/* Michael Introini
 * mbi2105
 * This class performs many different computations using loops on characters
 */
import java.util.Scanner;

public class Problem6Dot3 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		
		// Only the uppercase letters in the string.
		System.out.println("Problem 6.3 - A");
		String sA;
		System.out.println("Enter some text with upper case and lower case "
				+ "letters: ");
		sA = input.nextLine();
		for (int i = 0; i < sA.length(); i++) {
			if (Character.isUpperCase(sA.charAt(i)))
				System.out.print(sA.charAt(i) + " ");
		}


		// Every second letter of the string
		System.out.println("\n\nProblem 6.3 - B");
		String sB;
		System.out.println("Enter some text: ");
		sB = input.nextLine();
		System.out.print(sB.charAt(1));
		for (int i = 0; i < sB.length(); i++) {
			if (Character.isSpaceChar(sB.charAt(i)))
				System.out.print(sB.charAt(i+2));
		}


		// The string, with all vowels replaced by an underscore.
		System.out.println("\n\nProblem 6.3 - C");
		String sC;
		System.out.println("Enter some text: ");
		sC = input.nextLine();
		String sCLower = sC.toLowerCase();
		for (int i = 0; i < sCLower.length(); i++) {
			char ch = sCLower.charAt(i);
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
				sCLower = sCLower.replace(ch, '_');

		}
		System.out.println(sCLower);


		// The number of vowels in the string.
		System.out.println("\nProblem 6.3 - D");
		String sD;
		int count = 0;

		System.out.println("Enter some text: ");
		sD = input.nextLine();
		String sDLower = sD.toLowerCase();
		for (int i = 0; i < sDLower.length(); i++) {
			char ch = sDLower.charAt(i);
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
				count++;
		}
		System.out.println(count);


		// The positions of all vowels in the string.
		System.out.println("\nProblem 6.3 - E");
		String sE;

		System.out.println("Enter some text: ");
		sE = input.nextLine();
		String sELower = sE.toLowerCase();
		for (int i = 0; i < sELower.length(); i++) {
			char ch = sELower.charAt(i);
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
				System.out.println(ch + " at position: " + i);
		}
	}

}
