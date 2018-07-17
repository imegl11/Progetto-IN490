package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Alice extends primes.Item<Token> {
	private BigInteger q, g, key, h;
	
	/**
	 * Costruttore di Alice prende in input  
	 * @param args[0] -> bound, numero grande da cui inizia la ricerca di primi di Sophie Germain;
	 * @param args[1] -> maxprime, numero di primi da generare ad ogni iterazione di priming;
	 * @param args[2] -> iterations, numero di iterazioni per il test di Solovay Strassen;
	 * e li utilizza per creare un nuovo Sieve di Sophie.
	 * Prende il primo q di Sophie-Germain da tale Sieve e trova un generatore g di (Zq)*.
	 * Sfruttando i metodi definiti nel Sieve definisce una chiave pubblica h e una privata key.
	*/	
	public Alice(String[] args) {
		super(new Sieve(args));
		System.out.println("Alice creation");
		this.q = this.prime();
		this.g = this.generator(q);
		this.key = ((Sieve)this.next).pickRand(q); //Private key
		this.h = ((Sieve)this.next).modularExponentiation(g, key, q); //Public key
	}
	
//Setters
	
	/**
	 * Metodo prime prende da Sieve in Sophie il primo p di Sophie e restituisce il primo q = 2p + 1
	 * @return q
	 */
	private BigInteger prime() {
		BigInteger p, q;
		p = this.next.get().value();
		q = p.multiply(new BigInteger("2")).add(BigInteger.ONE);
		return q;		
	}
	
	/**
	 * @param q -> prende in input un primo q di Sophie-Germain
	 * @return g -> trova un generatore g di (Zq)*
	 */
	private BigInteger generator(BigInteger q) {
		BigInteger t;
		t = q.divide(new BigInteger("2"));
		while(((Sieve)this.next).legendre(t, q) != -1)
			t = t.add(BigInteger.ONE);
		return t;
	}
	
	/**
	 * Algoritmo per decifrare il messaggio criptato. Prende in input 
	 * @param C oggetto di tipo CipherText, rappresenta il messaggio cifrato.
	 */
	public void decryption(CipherText C) {
		BigInteger s, m;
		System.out.println("Decryption in progress");
		s = ((Sieve)this.next).modularExponentiation(C.value1(), this.key, this.getPrime());
		m = C.value2().multiply(s.modInverse(this.getPrime())).mod(this.getPrime());
		printMessage(m);
	}
	
//Getters
	
	public BigInteger getPrime() {
		return this.q;
	}
	
	public BigInteger getGenerator() {
		return this.g;
	}
	
	public BigInteger value() {
		return this.h;
	}
	
	private void printMessage(BigInteger n) {
		System.out.println("Alice: decrypted message m = "+n);
		System.out.println(" ");
	}
	
	public void print() {
		System.out.println("Alice: prime "+q);
		System.out.println("Alice: generator "+g);
		System.out.println("Alice: private key x = "+key);
		System.out.println("Alice: public key "+h);
		System.out.println(" ");
	}
	
	public Token get() {
		return new Token();
	}
}
	
	
