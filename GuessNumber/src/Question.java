import java.util.ArrayList;
import java.util.Random;

import edu.hk.csie.u100b219.finalproject.models.Guess;
import edu.hk.csie.u100b219.finalproject.models.Number;

public class Question implements IQuestion {

	private static ArrayList<Number> allNumbers;

	static {
		allNumbers = new ArrayList<Number>();
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				for (int k = 1; k <= 9; k++) {
					if (i != j && i != k && j != k) {
						try {
							allNumbers.add(new Number(i, j, k));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private static Number genNumber() {
		Random rnd = new Random();
		return allNumbers.get(rnd.nextInt(allNumbers.size()));
	}

	// <= end static

	private Number mNumber;
	private int mGuessCount;

	public Question() {
		mNumber = genNumber();
		mGuessCount = 0;
	}

	@Override
	public Guess guess(Number num) {
		mGuessCount++;
		return mNumber.compare(num);
	}

	@Override
	public int getGuessCount() {
		return mGuessCount;
	}

}
