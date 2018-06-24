package primes;

import java.math.BigInteger;

public abstract class Sieve<T> extends Item<T> {
	private BigInteger maxprime;
	private BigInteger euler;

//constructors
	public Sieve(String[] args, Item<T> next) {
		super(next);
		
		this.maxprime = new BigInteger(args[0]);
		this.euler = BigInteger.ZERO;
	}
	
	public Sieve() {
		super(null);
		this.euler = BigInteger.ZERO;
	}
	
//setters
	public void setmax(BigInteger max) {
		this.maxprime = max;
	}
	
	public void seteuler() {
		this.euler = this.euler.add(BigInteger.ONE);
	}
	
//getters
	public BigInteger getmax() {
		return this.maxprime;
	}
	
	public void print2() {
		this.print();
	}
	
	public void print() {
		System.out.print("S:"+this.euler+"->");
		this.next.print();
	}

	public BigInteger value() {
		return this.euler;
	}
	
	public abstract	T get();
	public abstract	boolean testloop(T token) ;
	public abstract void mainloop() ;

}
