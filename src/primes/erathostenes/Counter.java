package primes.erathostenes ;

import java.math.BigInteger ;
//test commit
public class Counter extends primes.Counter<Token> {
/*
private    BigInteger count ;
private    T tok;
*/
 
 // costruttori
	public Counter() {
		this(new Token());
	}
	
	public Counter(Token token) {
		super(token);
	}
	
/*	public Counter(Sieve sieve) {
		super(sieve);
	}
*/
	/*
    // setters
public	void set() {
		this.count = this.count.add(BigInteger.ONE) ;
	}

    // getters
	
public void print() {
		System.out.println("C:"+this.count);
	}
*/
	 
	 public Token get()
	 {
	 
	 this.token().Set(this.value());
	 this.set() ;
	 
	 return this.token() ;
	 }

/*
public	BigInteger value() {
		return this.count ;
	}
	
public 	T token() {
		return this.tok;
	}
 
 */

}
