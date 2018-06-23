package primes.erathostenes ;

import java.math.BigInteger ;

public class Token {
private BigInteger num ;
	//costruttori
	public Token() {
		System.out.println("new erathostenes Token");
		
		this.num = BigInteger.ZERO ;
		
	}

	// setters
public	void Set(BigInteger n) {
		
		this.num = n ;
		
		
	}
	
	
	// getters
	public BigInteger value() {
		
		return this.num;
	}

	public BigInteger value2() {
		// TODO Auto-generated method stub
		return null;
	}
	
	



}
