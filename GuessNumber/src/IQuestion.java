import edu.hk.csie.u100b219.finalproject.models.Guess;
import edu.hk.csie.u100b219.finalproject.models.Number;


public interface IQuestion {
	public Guess guess(Number num);
	public int getGuessCount();
}
