import java.util.Random;
import eduni.distributions.*;

public class edunitest {

	public static void main(String[] args) {
		
		
		Random r = new Random();
		int luvut = 0;
		double summa = 0;
		
		Normal luku = new Normal(r.nextInt(50, 70), r.nextInt(75, 125));
		Normal luku2 = new Normal(1, 1);
		Negexp luku3 = new Negexp(10,5);
		
		//System.out.println(luku.sample());
		//System.out.println(luku2.sample());
		//System.out.println(luku3.sample());
		
		for(int i = 0; i < 10; i++) {
			Normal test = new Normal(7, 1);
			summa += test.sample();
			System.out.println(test.sample());
		}

	}

}
