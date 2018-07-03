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

	/**
	*if return true, the mainloop will go on
	*/
	public boolean test(Token t) {
		boolean answer = divide(t.value());
		if (t.priming() == false && answer == false)
			answer = this.modularRestriction(t.value());
		//debug lines
		/*	if (!t.priming() && answer && super.value().compareTo(BigInteger.TEN)>0) {
				System.out.println("modularRestriction "+ super.value()+" failed");
			}*/
		return answer;
	}

	private boolean modularRestriction(BigInteger p) {
		BigInteger tmp = p.subtract(BigInteger.ONE).divide(new BigInteger("2"));
		return tmp.mod(super.value()).compareTo(BigInteger.ZERO) == 0;
	}

	private boolean divide (BigInteger n) {
		return (n.mod(super.value()).compareTo(BigInteger.ZERO) == 0);
	}

}
