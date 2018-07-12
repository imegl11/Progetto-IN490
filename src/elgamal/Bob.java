package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Bob extends primes.Item<Token> {
	private BigInteger y, m, s, c1, c2;
	
	public Bob(Alice A) { 
		super(A);
		System.out.println("Creazione Bob");
		this.m = ((Sieve)A.next).pickRand(A.getPrime());
		print();
		CipherText C = encryption(A);
		A.decryption(C);
	}
	
	private CipherText encryption(Alice A) {
		y = ((Sieve)A.next).pickRand(A.getPrime());
		c1 = ((Sieve)A.next).modularExponentiation(A.getGenerator(), y, A.getPrime());
		s = ((Sieve)A.next).modularExponentiation(A.value(), y, A.getPrime());
		c2 = this.m.mod(A.getPrime()).multiply(s).mod(A.getPrime());
		System.out.println("Bob: y = "+y+", s = "+s);
		CipherText C = new CipherText(c1, c2);
		return C;
	}
	
	public void print() {
		System.out.println("Bob's plaintext message: "+m); //messaggio in chiaro
	}
	
	public Token get() {
		return new Token();
	}
	
	public BigInteger value() {
		return this.c2;
	}
}