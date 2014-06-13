package edu.hk.csie.u100b219.finalproject.models;

public class Guess {

	private final Number mNumber;
	private final int mA;
	private final int mB;

	public Guess(Number num, int a, int b) throws Exception {
		mNumber = num;
		mA = a;
		mB = b;
		checkAB();
	}

	public Guess(int num, int a, int b) throws Exception {
		mNumber = new Number(num);
		mA = a;
		mB = b;
		checkAB();
	}

	public Guess(String num, int a, int b) throws Exception {
		mNumber = new Number(num);
		mA = a;
		mB = b;
		checkAB();
	}
	
	private void checkAB() throws Exception {
		if (mA + mB > 3) {
			throw new Exception("A + B 的數量不對吧..?");
		}
	}

	public Number getNumber() {
		return mNumber;
	}

	public int getA() {
		return mA;
	}

	public int getB() {
		return mB;
	}

	public boolean compare(Number num) {
		Guess gnum = mNumber.compare(num);

		if (gnum.getA() == mA && gnum.getB() == mB)
			return true;
		return false;
	}

}
