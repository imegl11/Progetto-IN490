package primes.erathostenes.sophie;

import java.math.BigInteger;

public class Token extends primes.erathostenes.Token {
	//se true, priming indica che stiamo cercando numeri primi piccoli per i filtri
	private boolean priming;

//constructors
	public Token() {
		super();
	}

// setters
	
// getters
	public boolean priming() {
		return this.priming;
	}

}
