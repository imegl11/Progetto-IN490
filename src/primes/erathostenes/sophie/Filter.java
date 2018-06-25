package primes.erathostenes.sophie;

import java.math.BigInteger;
import primes.Item;
import primes.erathostenes.Token;

public class Filter extends primes.erathostenes.Filter implements DoubleMode {
	private boolean priming;
//constructors
	public Filter(Item<Token> tail, BigInteger p ) {
		super(tail,p);
		this.priming = true;
	}

//setters
	private void mode() {
		this.priming = !this.priming;
	}

//getters
	public boolean test(Token t) {
		boolean answer = super.test(t);
		if (this.priming == false && answer == true)
			answer = this.modularRestriction(t.value());
		return answer;
	}

	private boolean modularRestriction(BigInteger p) {
		BigInteger temp = super.value().subtract(BigInteger.ONE).divide(new BigInteger("2"));
		return(p.mod(temp).compareTo(BigInteger.ZERO) != 0);
	}

	public Token get(boolean priming) {
		Token tok;
		this.mode();
		tok = super.get();
		this.mode();
		return tok;
	}

}
