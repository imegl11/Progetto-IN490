package primes.erathostenes.sophie;

import java.math.BigInteger;
import primes.erathostenes.Token;

public class Counter extends primes.erathostenes.Counter {
	//bound è il primo numero resistuito nella generazione dei primi di Sophie Germain
	private BigInteger bound;
	private boolean priming;

//constructors
	/**
	* @param bound Valore da cui iniziare la ricerca di primi grandi. È sempre dispari.
	*/
	public Counter(BigInteger bound) {
		super(new Token());
		if (bound.mod(new BigInteger("2")).compareTo(BigInteger.ZERO)==0)
			this.setBound(bound.add(BigInteger.ONE));
		else
			this.setBound(bound); 
	}

//setters
	private void setBound(BigInteger bound) {
		this.bound = bound;
	}

	void setPriming(boolean b) {
		this.priming = b;
	}
	
//getters
	/**
	* @param priming Indica la modalità, true per la generazione dei filtri, false per la ricerca dei primi. È sempre false.
	* @return token Token contenente il valore opportuno (bound o count).
	*/
	 public Token get() {
	 	if (this.priming == true)
	 		this.get();
	 	else {
	 		this.token().Set(this.bound);
	 		this.setBound(this.bound.add(new BigInteger("2")));
	 	}
		return this.token();
	 }
}
