package sophie;

import java.math.BigInteger;
import primes.Item;

public class Filter extends erathostenes.Filter {

//constructors
	public Filter(Item<Token> tail, BigInteger p ) {
		super(tail,p);
	}

//setters

//getters
	public boolean test(Token t) {
		boolean answer = super.test(t);
		if (t.priming() == false && answer == true)
			answer = this.modularRestriction(t.value());
		return answer;
	}

	private boolean modularRestriction(BigInteger p) {
		BigInteger temp = super.value().subtract(BigInteger.ONE).divide(new BigInteger("2"));
		return(p.mod(temp).compareTo(BigInteger.ZERO) != 0);
	}

}
