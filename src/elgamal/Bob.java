package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Bob extends primes.Item<Token> {
	private BigInteger y, m, s, c1, c2;
	
	public Bob(Alice A, BigInteger m) { 
		super(A);
		this.m = m;
		printMessage(m);
		CipherText C = encryption(A);
		A.decryption(C);
	}
	
	private CipherText encryption(Alice A) {
		y = ((Sieve)A.next).pickRand(A.getPrime());
		c1 = ((Sieve)A.next).modularExponentiation(A.getGenerator(), y, A.getPrime());
		s = ((Sieve)A.next).modularExponentiation(A.value(), y, A.getPrime());
		c2 = this.m.multiply(s);
		System.out.println("Bob: y = "+y+", s = "+s);
		CipherText C = new CipherText(c1, c2);
		return C;
	}
	
	private void printMessage(BigInteger n){
		System.out.println("Bob's plaintext message: "+n); //messaggio in chiaro
	}
	
	public void print() {}
	
	public Token get() {
		return new Token();
	}
	
	public BigInteger value() {
		return this.c2;
	}
}