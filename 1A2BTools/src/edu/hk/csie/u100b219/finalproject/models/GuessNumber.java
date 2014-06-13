package edu.hk.csie.u100b219.finalproject.models;
import java.util.ArrayList;
import java.util.Random;

public class GuessNumber {
	public final ArrayList<Number> numbers;
	public final ArrayList<Guess> guessNumbers;

	public GuessNumber() {
		this.numbers = new ArrayList<Number>();
		this.guessNumbers = new ArrayList<Guess>();
		this.genList();
	}

	public void clearAll() {
		this.numbers.clear();
		this.guessNumbers.clear();
		this.genList();
	}

	private void genList() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				for (int k = 1; k <= 9; k++) {
					if (i != j && i != k && j != k) {
						try {
							this.numbers.add(new Number(i, j, k));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void guess(Guess guess) {
		guessNumbers.add(guess);
		for (int i = 0; i < numbers.size(); i++) {
			Number num = numbers.get(i);
			if (!guess.compare(num)) {
				numbers.remove(i);
				i--;
			}
		}
		NumberSorter.sortNumber(numbers);
	}

	public Number genNumber() {
		Random rnd = new Random();
//		return numbers.get(rnd.nextInt(numbers.size()));
//		return numbers.get(0);
		int minRank = numbers.get(0).getRank();
		int maxIndex = 0;
		do {
			maxIndex++;
			if (numbers.size() <= maxIndex)
				break;
		} while (numbers.get(maxIndex).getRank() == minRank);
		return numbers.get(rnd.nextInt(maxIndex));
	}

}
