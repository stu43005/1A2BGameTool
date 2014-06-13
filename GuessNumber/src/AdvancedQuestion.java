import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import edu.hk.csie.u100b219.finalproject.models.Guess;
import edu.hk.csie.u100b219.finalproject.models.GuessNumber;
import edu.hk.csie.u100b219.finalproject.models.Number;

public class AdvancedQuestion implements IQuestion {

	private static Comparator<SimpleEntry<Integer, Integer>> valueComparator = new Comparator<SimpleEntry<Integer, Integer>>() {
		@Override
		public int compare(SimpleEntry<Integer, Integer> lhs, SimpleEntry<Integer, Integer> rhs) {
			return rhs.getValue() - lhs.getValue();
		}
	};

	private final GuessNumber gn;

	private int mGuessCount;

	public AdvancedQuestion() {
		gn = new GuessNumber();
	}

	@Override
	public Guess guess(Number num) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Number number : gn.numbers) {
			Guess guess = number.compare(num);
			int i = (guess.getA() << 2) + guess.getB();
			Integer old = map.get(i);
			if (old == null) {
				old = 0;
			}
			map.put(i, old + 1);
		}

		ArrayList<SimpleEntry<Integer, Integer>> list = new ArrayList<SimpleEntry<Integer, Integer>>();
		Set<Entry<Integer, Integer>> set = map.entrySet();
		for (Entry<Integer, Integer> entry : set) {
			list.add(new SimpleEntry<Integer, Integer>(entry.getKey(), entry.getValue()));
		}

		Collections.sort(list, valueComparator);

		int ab = list.get(0).getKey();
		int a = (ab & 0xC) >> 2;
		int b = ab & 0x3;

		try {
			Guess resultGuess = new Guess(num, a, b);
			gn.guess(resultGuess);
			mGuessCount++;
			return resultGuess;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getGuessCount() {
		return mGuessCount;
	}

}
