package primes;

import java.math.BigInteger;

public abstract class Counter<T> extends Item<T> {
	private BigInteger count;
	private T tok;

//constructors
	public Counter() {
		super();
		this.count = new BigInteger("2");
	}

	public Counter(T token) {
		super();
		this.tok = token;
		this.count = new BigInteger("2");
	}

	public Counter(Sieve<T> sieve) {
		this();
	}
	
//setters
	public void set() {
		this.count = this.count.add(BigInteger.ONE);
	}

//getters
	public void print() {
		System.out.println("C:"+this.count);
	}
	
	public abstract	T get();
	
	public BigInteger value() {
		return this.count;
	}
	
	public T token() {
		return this.tok;
	}

}
