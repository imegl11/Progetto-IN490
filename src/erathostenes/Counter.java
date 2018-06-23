package primes.erathostenes;

import java.math.BigInteger;

public class Counter extends primes.Counter<Token> {

		//constructors
	public Counter() {
		this(new Token());
	}
	
	public Counter(Token token) {
		super(token);
	}

		//setters
	
		//getters
	 public Token get() {
		this.token().Set(this.value());
		this.set();
		return this.token();
	 }
}
