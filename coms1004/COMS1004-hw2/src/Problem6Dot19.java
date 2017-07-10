/* Michael Introini
 * mbi2105
 * This is a Monty Hall Paradox game where there is a car hidden behind
 * on of 3 doors. The contestant chooses one of the doors while the host
 * chooses a different door that might contain the car or a goat. The
 * contestant then has the option to switch his choice to that of the
 * host. This program captures how many times out of 1000 games the contestan
 * wins after switching, and how many times he wins sticking with their
 * original choice.
 */
import java.util.Random;
public class Problem6Dot19 {
	public static void main(String[] args) {

		// Instance Variables
		final int DOOR1 = 1, DOOR2 = 2, DOOR3 = 3;
		final int YES = 1;
		int strategy1 = 0;
		int strategy2 = 0;
		int hostPick;
		int playerPick;
		int whereIsTheCar;
		int switchChoice;
		int playCount = 0;

		Random generator = new Random();

		// Simulate the game 1000 times
		while (playCount < 1000) {
			// Car is placed behind a random door
			whereIsTheCar = (int) (Math.random() * 3) + 1;
			// Player picks a random door
			playerPick = (int) (Math.random() * 3) + 1;
			// Host picks a door that player hasn't picked
			hostPick = (int) (Math.random() * 3) + 1;
			if (playerPick == DOOR1) {
				hostPick = generator.nextInt(1) + 2;
			}
			if (playerPick == DOOR2) {
				hostPick = generator.nextInt(2) + 1;
				while (hostPick == 2) {
					hostPick = generator.nextInt(2) + 1;
				}
			}
			if (playerPick == DOOR3)  {
				hostPick = generator.nextInt(1) + 1;
			}

			// Player exercises choice to switch or not
			switchChoice = (int) (Math.random() * 2) + 1;
			if (switchChoice ==  YES) {
				playerPick = hostPick;
				// Determine if the player won after switching
				if (playerPick == whereIsTheCar) {
					strategy1++;
				}
			} else {
				// Determine if the player won with the original choice
				if (playerPick == whereIsTheCar) {
					strategy2++;
				}
			}
		playCount++;
		}

		System.out.println("Strategy 1: " + strategy1);
		System.out.println("Strategy 2: " + strategy2);
		System.out.println("Total PLay Count: " + playCount);

	}
}
