package primes.erathostenes.sophie;

import java.math.BigInteger;
import primes.erathostenes.sophie.Token;

public class Counter extends primes.Counter<Token> {
	//bound è il primo numero resistuito nella generazione dei primi di Sophie Germain
	private BigInteger bound;

//constructors
	/**
	* @param value Valore da cui iniziare la ricerca di primi grandi. È sempre dispari.
	*/
	public Counter(BigInteger value) {
		super(new Token());
		System.out.println("Created Sophie Counter");

		if (value.mod(new BigInteger("2")).compareTo(BigInteger.ZERO)==0)
			this.setBound(value.add(BigInteger.ONE));
		else
			this.setBound(value);
	}

//setters
	private void setBound(BigInteger value) {
		this.bound = value;
	}
	
//getters
	public BigInteger bound() {
		return this.bound;
	}
	public Token get() {
		if (token().priming()) {
			super.token().setValue(super.value());
			super.set();
		} else {
			super.token().setValue(this.bound());
			this.setBound(this.bound().add(new BigInteger("2")));
		}
		return token();
	}

}
