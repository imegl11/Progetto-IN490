package primes.erathostenes;

import java.math.BigInteger;
import primes.Item;

public class Filter extends primes.Filter<Token> {

//constructors
	public Filter(Item<Token> tail, BigInteger p ) {
		super(tail,p);
	}

//setters

//getters
	public boolean test(Token t) {
		return(t.value().mod(this.value()).compareTo(BigInteger.ZERO) == 0);
	}
	
	public Token get() {
		Token token;
		token=this.next.get();
		while (test(token)) token=next.get();
		return token;
	}

}
