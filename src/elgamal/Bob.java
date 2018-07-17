package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Bob extends primes.Item<Token> {
	private BigInteger mex, key;
	
	/**
	 * Costruttore di Bob che prende in input
	 * @param A oggetto della classe Alice
	 * da cui prende il primo di Sophie-Germain e crea un mex (messaggio in chiaro) e una chiave privata
	 */
	public Bob(Alice A) { 
		super(A);
		System.out.println("Bob creation");
		this.mex = ((Sieve)A.next).pickRand(A.getPrime()); //Plaintext message
		this.key = ((Sieve)A.next).pickRand(A.getPrime()); //Private key
	}
	
//Setters
	
	/**
	 * Algoritmo per cifrare il messaggio in chiaro. Prende in input
	 * @param A oggetto di tipo Alice
	 * e con i suoi attributi cifra mex (messaggio in chiaro) 
	 * @return C oggetto della classe Alice CipherText (messaggio cifrato)
	 */
	public CipherText encryption(Alice A) {
		BigInteger c1, s, c2;
		System.out.println("Encryption in progress");
		
		c1 = ((Sieve)A.next).modularExponentiation(A.getGenerator(), key, A.getPrime());
		s = ((Sieve)A.next).modularExponentiation(A.value(), key, A.getPrime());
		c2 = this.mex.mod(A.getPrime()).multiply(s).mod(A.getPrime());
		CipherText C = new CipherText(c1, c2);
		return C;
	}
	
//Getters
	
	public void print() {
		System.out.println("Bob: plaintext message m = "+mex);
		System.out.println("Bob: private key "+key);
		System.out.println(" ");
	}
	
	public Token get() {
		return new Token();
	}
	
	public BigInteger value() {
		return BigInteger.ZERO;
	}
}