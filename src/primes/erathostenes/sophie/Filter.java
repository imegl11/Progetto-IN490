package primes.erathostenes.sophie;

import java.math.BigInteger;
import primes.Item;
import primes.erathostenes.sophie.Token;

public class Filter extends primes.Filter<Token> {

//constructors
	public Filter(Item<Token> tail, BigInteger p) {
		super(tail,p);
	}

//setters

//getters
	public Token get() {
		Token tok = next.get();
		while (test(tok))
			tok = next.get();
		return tok;
	}

	public boolean test(Token t) {
		boolean answer = divide(t.value());
		if (t.priming() == false && answer == false)
			answer = this.modularRestriction(t.value());
		return answer;
	}

	private boolean modularRestriction(BigInteger p) {
		BigInteger temp = super.value().subtract(BigInteger.ONE).divide(new BigInteger("2"));
		return(p.mod(temp).compareTo(BigInteger.ZERO) != 0);
	}

	private boolean divide (BigInteger n) {
		return (n.mod(super.value()).compareTo(BigInteger.ZERO) == 0);
	}

}
