package edu.hk.csie.u100b219.finalproject.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class NumberSorter {

	private static class NumberCounter implements Entry<Integer, Integer> {
		private int mKey;
		private int mValue;
		private int mRank;

		public NumberCounter(Integer arg0, Integer arg1) {
			mKey = arg0;
			mValue = arg1;
		}

		@Override
		public Integer getKey() {
			return mKey;
		}

		@Override
		public Integer getValue() {
			return mValue;
		}

		@Override
		public Integer setValue(Integer value) {
			int oldValue = mValue;
			mValue = value;
			return oldValue;
		}

		public int getRank() {
			return mRank;
		}

		public void setRank(int rank) {
			mRank = rank;
		}

		public void addOne() {
			add(1);
		}

		public void add(int i) {
			mValue += i;
		}
	}

	private static class NumberCounterMap extends HashMap<Integer, NumberCounter> {
		private static final long serialVersionUID = 1L;

		private static Comparator<NumberCounter> numberValueComparator = new Comparator<NumberCounter>() {
			@Override
			public int compare(NumberCounter lhs, NumberCounter rhs) {
				return rhs.getValue() - lhs.getValue();
			}
		};

		public NumberCounterMap() {
			for (int i = 1; i <= 9; i++) {
				this.put(i, new NumberCounter(i, 0));
			}
		}

		public void calcRank() {
			ArrayList<NumberCounter> list = new ArrayList<NumberCounter>();
			Set<Entry<Integer, NumberCounter>> set = this.entrySet();
			for (Entry<Integer, NumberCounter> entry : set) {
				list.add(entry.getValue());
			}

			Collections.sort(list, numberValueComparator);

			int oldValue = -1;
			int rank = 0;
			for (NumberCounter counter : list) {
				if (oldValue != counter.getValue()) {
					rank++;
					oldValue = counter.getValue();
				}
				counter.setRank(rank);
			}
		}
	}

	private static Comparator<Number> numberRankComparator = new Comparator<Number>() {
		@Override
		public int compare(Number lhs, Number rhs) {
			return lhs.getRank() - rhs.getRank();
		}
	};

	public static void sortNumber(ArrayList<Number> numbers) {
		NumberCounterMap hundred = new NumberCounterMap();
		NumberCounterMap ten = new NumberCounterMap();
		NumberCounterMap one = new NumberCounterMap();

		for (Number num : numbers) {
			int[] numArr = num.getNumberArray();
			hundred.get(numArr[0]).addOne();
			ten.get(numArr[1]).addOne();
			one.get(numArr[2]).addOne();
		}

		hundred.calcRank();
		ten.calcRank();
		one.calcRank();

		for (Number num : numbers) {
			int[] numArr = num.getNumberArray();
			int rank = 0;
			rank += hundred.get(numArr[0]).getRank();
			rank += ten.get(numArr[1]).getRank();
			rank += one.get(numArr[2]).getRank();
			num.setRank(rank);
		}

		Collections.sort(numbers, numberRankComparator);
	}
}
