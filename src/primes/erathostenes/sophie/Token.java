package primes.erathostenes.sophie;

import java.math.BigInteger;

public class Token {
	private BigInteger value;
	private boolean priming;

//constructors
	public Token() {
		System.out.println("Token created");
		this.value = BigInteger.ZERO;
	}

//setters
	public void setValue(BigInteger n) {
		this.value = n;
	}

	public void setPriming(boolean b) {
		this.priming = b;
	}
	
//getters
	public BigInteger value() {
		return this.value;
	}

	public boolean priming() {
		return this.priming;
	}
}
