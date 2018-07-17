package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Eve extends primes.Item<Token> {
	private BigInteger q, g, h, key, mex;
	
	/**
	 * Costruttore di Eve prende in input
	 * @param A oggetto della classe Alice
	 */
	public Eve(Alice A) {
		super(A);
		System.out.println("Eve creation");
		q = A.getPrime();
		g = A.getGenerator();
		h = A.value();
	}
	
//Setters
	/**
	 * Metodo di attacco al Crittosistema di Elgamal
	 * @param C oggetto della classe CipherText
	 */
	public void attack(CipherText C) {
		BigInteger s;
		key = shanks();
		s = ((Sieve)this.next.next).modularExponentiation(C.value1(), this.key, q);
		mex = C.value2().multiply(s.modInverse(q)).mod(q);	
	}
	
	/**
	 * Algoritmo di Shanks
	 * @return key chiave privata di Alice ottenuta con l'attacco (logaritmo discreto della chiave pubblica di Alice)
	 */
	private BigInteger shanks() {
		BigInteger n, inv;
		int intn, i, k;
		boolean flag = false;
		System.out.println("Attack in progress");
	
		n = squareRoot(q.add(new BigInteger("-1"))).add(BigInteger.ONE); 
		intn = n.intValue();
		BigInteger[] a = bigVector(intn);
		BigInteger[] b = new BigInteger[intn];
		BigInteger[] c = bigVector(intn);
		BigInteger[] d = new BigInteger[intn];	
		
		for(i = 0; i < intn; i++) {
			b[i] = ((Sieve)this.next.next).modularExponentiation(g, BigInteger.valueOf(i).multiply(n), q);
			inv = ((Sieve)this.next.next).modularExponentiation(g, BigInteger.valueOf(i), q).modInverse(q);
			d[i] = h.multiply(inv).mod(q);
		}
		bubbleSort(a, b, intn);
		bubbleSort(c, d, intn);
		
		for(i = 0; i < intn && flag == false; i++) {
			for(k = 0; k < intn; k++) {
				if(b[i].compareTo(d[k]) == 0) {
					key = n.multiply(a[i]).add(c[k]).mod(q.add(new BigInteger("-1")));
					k = intn;
					flag = true;
				}
			}
		}
		return key;
	}
	
	private BigInteger squareRoot(BigInteger t) {
		BigInteger r;
		int i;
		r = t.divide(new BigInteger("2"));
		for(i = 0; i < 30; i++)
			r = (r.add(t.divide(r))).divide(new BigInteger("2"));
		return r;
	}
	
	private BigInteger[] bigVector(int length) {
		BigInteger[] a = new BigInteger[length];
		int i;
		for(i = 0; i < length; i++) 
			a[i] = BigInteger.valueOf(i);
		return a;
	}
	
	private void bubbleSort(BigInteger[] a, BigInteger[] b, int length) {
		int i, j;
		for(i = 0; i < length; i++) {
			boolean flag = false;
			for(j = 0; j < length-1; j++) {
				if(b[j].compareTo(b[j+1]) == 1) { //Se l'elemento j è maggiore del successivo allora scambiamo i valori
					change(a, j);
					change(b, j);
					flag = true; //Lo setto a true per indicare che é avvenuto uno scambio
				}
			}
			if(!flag) break; //Se nell'ultima iterazione non ci sono stati scambi può terminare (array ordinato)
		}
	}
	
	private void change(BigInteger[] a, int i) {
		BigInteger tmp = a[i];
		a[i] = a[i+1];
		a[i+1] = tmp;
	}
	
//Getters
	
	public void print() {
		System.out.println("Eve: Alice's private key found x = "+key);
		System.out.println("Eve: Bob's plaintext message found m = "+mex);
	}
	
	public Token get() {
		return new Token();
	}
	
	public BigInteger value() {
		return this.key;
	}
}
