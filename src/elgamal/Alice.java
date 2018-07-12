package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Alice extends primes.Item<Token> {
	private BigInteger q, g, h, x;
	
	/**
	* @param args[0] -> maxprime, numero di primi da generare ad ogni iterazione di priming;
	* @param args[1] -> bound, numero grande da cui inizia la ricerca di primi di Sophie Germain;
	* @param args[2] -> iterations, numero di iterazioni per il test di Solovay Strassen.
	*/	
	
	public Alice(String[] args) {
		super(new Sieve(args));
		System.out.println("Creazione Alice");
		this.q = this.prime();
		this.g = this.generator(q);
		this.x = ((Sieve)this.next).pickRand(q); // Private key
		this.h = ((Sieve)this.next).modularExponentiation(g, x, q); // Public key
		print();
	}
	
//setters
	private BigInteger prime() {
		BigInteger p, q;
		p = this.next.get().value();
		q = p.multiply(new BigInteger("2")).add(BigInteger.ONE);
		return q;		
	}
	
	private BigInteger generator(BigInteger q) {
		x = q.divide(new BigInteger("2"));
		while(((Sieve)this.next).legendre(x, q) != -1)
			x = x.add(BigInteger.ONE);
		return x;
	}
	
	public void decryption(CipherText C) {
		BigInteger s, m;
		s = ((Sieve)this.next).modularExponentiation(C.value1(), this.x, this.getPrime());
		m = C.value2().multiply(s.modInverse(this.getPrime())).mod(this.getPrime());
		printMessage(m);
	}
	
//getters
	public BigInteger getPrime() {
		return this.q;
	}
	
	public BigInteger getGenerator() {
		return this.g;
	}

	public BigInteger value() {
		return this.h;
	}
	
	public void printMessage(BigInteger n) {
		System.out.println("Alice's decrypted message: "+n);
	}
	
	public void print() {
		System.out.println("Alice: q = "+q+", g = "+g+", x = "+x+", h = "+h);
	}
	
	public Token get() {
		return new Token();
	}
	
}
	
	
