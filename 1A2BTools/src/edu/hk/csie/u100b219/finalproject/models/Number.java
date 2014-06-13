package edu.hk.csie.u100b219.finalproject.models;

public class Number {

	private final int mNumber;
	private final int[] mNumberArray;
	private int mRank = 0;

	public Number(int num) throws Exception {
		mNumber = num;
		mNumberArray = getNumberArray(mNumber);
		checkNumber();
	}

	public Number(int hun, int ten, int one) throws Exception {
		mNumber = hun * 100 + ten * 10 + one;
		mNumberArray = getNumberArray(mNumber);
		checkNumber();
	}

	public Number(String str) throws Exception {
		int num;
		try {
			num = Integer.valueOf(str);
		} catch (Exception e) {
			num = 0;
		}
		mNumber = num;
		mNumberArray = getNumberArray(mNumber);
		checkNumber();
	}

	private void checkNumber() throws Exception {
		int[] numArr = getNumberArray();
		if (numArr[0] == 0 || numArr[1] == 0 || numArr[2] == 0 || numArr[0] == numArr[1] || numArr[1] == numArr[2]
				|| numArr[0] == numArr[2]) {
			throw new Exception("¼Æ¦r¦³ÂI©_©Ç­C");
		}
	}

	public int getNumber() {
		return mNumber;
	}

	public int[] getNumberArray() {
		return mNumberArray;
	}

	public void setRank(int rank) {
		mRank = rank;
	}

	public int getRank() {
		return mRank;
	}

	@Override
	public String toString() {
		//return String.valueOf(mRank);
		return mNumber + " - rank=" + mRank;
	}

	public Guess compare(Number num) {
		int strike = 0, ball = 0;
		int[] numArr = num.getNumberArray();

		if (numArr[0] == mNumberArray[0])
			strike++;
		if (numArr[1] == mNumberArray[1])
			strike++;
		if (numArr[2] == mNumberArray[2])
			strike++;
		if (numArr[0] == mNumberArray[1] || numArr[0] == mNumberArray[2])
			ball++;
		if (numArr[1] == mNumberArray[0] || numArr[1] == mNumberArray[2])
			ball++;
		if (numArr[2] == mNumberArray[0] || numArr[2] == mNumberArray[1])
			ball++;

		try {
			return new Guess(num, strike, ball);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static int[] getNumberArray(int num) {
		int[] arr = new int[3];
		arr[0] = (int) Math.floor(num / 100) % 10;
		arr[1] = (int) Math.floor(num / 10) % 10;
		arr[2] = num % 10;
		return arr;
	}
}
