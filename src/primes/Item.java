package primes;

import java.math.BigInteger;

public abstract class Item<T> {
	public Item<T> next;

//constructors
	public Item () {
		this.set(null);
	}

	public Item (Item<T> tail) {
		this.set(tail);
	}

//setters
	public void set (Item<T> list) {
		this.next = list;
	}

//getters
	int length() {
		Item<T> lista = this;
		int i = 0;
	
		while((lista != null)) {
			i++;
			lista = lista. next;
		}
		
		return i;
	}
	
	public Item<T> next() {
		return next;
	}
	
	int rlength() {
		if (this.next==null)
			return 1;
		else
			return (next.rlength()+1);
	}

	abstract public void print();
		
	abstract public T get();
		
	abstract public BigInteger value();

}
