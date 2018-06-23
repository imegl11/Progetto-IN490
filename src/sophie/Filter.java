package primes.erathostenes ;

import java.math.BigInteger ;
import primes.Item ;

public class Filter extends primes.Filter<Token> {
 //private BigInteger prime ;

    // costruttori

 public Filter(Item<Token> tail, BigInteger p ) {
	super (tail,p) ;
    }

	
/*
 // setters
private void Set(Item<Token> tail, BigInteger p) {
	this.prime = p ;
	super.set(tail) ;
    }
 
    // getters
public void print() {
		
		System.out.print("F:"+this.prime+"->");
		//if (!(this.next==null))
		this.next.print() ;
		
	}
*/
public boolean test(Token t) {
		return (t.value().mod(this.value()).compareTo(BigInteger.ZERO) == 0) ;
	}
	
public Token get() {
		Token token;
		token=this.next.get() ;
		while (test(token)) token=next.get();
		return token;

	}
	
	
/*public	BigInteger value() {
		return this.prime ;
		
	}
*/
}










