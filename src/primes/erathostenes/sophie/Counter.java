package primes.erathostenes.sophie;

import java.math.BigInteger;

public class Counter extends primes.erathostenes.Counter {
	//bound è il primo numero resistuito nella generazione dei primi di Sophie Germain
	private BigInteger bound;

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
	
//getters
	/**
	* @return token Token contenente il valore di un primo relativamente piccolo, per generare i filtri, se token.priming è true, altrimenti contenente un candidato primo di Sophie Germain molto grande.
	*/
	 public Token get() {
	 	if (this.token().priming() == true)
	 		this.get();
	 	else {
	 		this.token().Set(this.bound);
	 		this.setBound(this.bound.add(new BigInteger("2")));
	 	}
		return this.token();
	 }
}
