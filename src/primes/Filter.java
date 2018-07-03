package primes;

import java.math.BigInteger;

public abstract class Filter<T> extends Item<T> {
	private BigInteger prime;

//constructors
	public Filter(Item<T> tail, BigInteger p ) {
		//System.out.println("new primes Filter");
		this.Set(tail,p);
  }

//setters
	public void Set(Item<T> tail, BigInteger p) {
		this.prime = p;
		super.set(tail);
	}

//getters
	public void print() {
		System.out.print("F:"+this.prime+"->");
		this.next.print();
	}

	public abstract boolean test(T t);

	public abstract T get();

	public	BigInteger value() {
		return this.prime;
	}

}
