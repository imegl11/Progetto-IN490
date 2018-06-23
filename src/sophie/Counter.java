package primes.sophie;

import java.math.BigInteger;
import primes.erathostenes;

public class Counter extends erathostenes.Counter {
	//bound è il primo numero resistuito nella generazione dei primi di Sophie Germain
	private BigInteger bound;

		//constructors
	public Counter(BigInteger bound) {
		super(new Token);
		this.setBound(bound); 
	}

		//setters
	private setBound(BigInteger bound) {
		this.bound = bound;
	}
	
		//getters

	/**
	* @return token Token contenente il valore di un primo relativamente piccolo, per generare i filtri, se token.priming è true, altrimenti contenente un candidato primo di Sophie Germain molto grande.
	*/
	 public Token get() {
	 	if (super.token().priming == true)
	 		super.get();
	 	else {
	 		super.token().Set(this.bound);
	 		this.setBound(this.bound.add(new BigInteger("2")));
	 	}
		return this.token();
	 }
}