import java.util.Scanner;

import edu.hk.csie.u100b219.finalproject.models.Guess;
import edu.hk.csie.u100b219.finalproject.models.Number;

public class QuestionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("�}�l�q�a");
		IQuestion question = new Question();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int input = scanner.nextInt();
			if (input == 0)
				break;
			try {
				Number number = new Number(input);
				Guess guess = question.guess(number);
				System.out.println(String.format("%dA %dB", guess.getA(), guess.getB()));
				if (guess.getA() == 3) {
					System.out.println("�q���F�I �q�F" + question.getGuessCount() + "��");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("�����F");
	}

}
