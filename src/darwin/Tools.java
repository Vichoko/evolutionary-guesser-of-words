package darwin;

import java.util.Random;

public class Tools {
	static char getRandomChar() {
		Random rand = new Random();
		int randomNum = rand.nextInt(26); // english alphabet
		char mutation = (char) ('a' + randomNum);
		return mutation;
	}
}
