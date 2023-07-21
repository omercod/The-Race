package utilities;
import java.util.Random;

/**
 * This class grilled events that reflect on the competition result events.
 * @author Omer Cohen 208627299
 */

public class Fate {

	private static Random rand = new Random();

	public static boolean breakDown() {
		return rand.nextInt(10) > 8;
	}

	public static boolean generateFixable() {
		return rand.nextInt(10) > 5;
	}

	public static Mishap generateMishap() {
		return new Mishap(generateFixable(), generateTurns(), generateReduction());
	}

	private static float generateReduction() {
		return rand.nextFloat();
	}

	private static int generateTurns() {
		return rand.nextInt(5) + 1;
	}

	public static void setSeed(int seed) {
		rand.setSeed(seed);
	}

}
