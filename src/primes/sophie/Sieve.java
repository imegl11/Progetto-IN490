package primes.sophie;

import java.math.BigInteger;
import java.util.Random;

public class Sieve extends primes.Sieve<Token> {
	private BigInteger sophie;
	private int offset;
	private int iterations;

//constructors
	/**
	* @param args[0] -> bound, numero grande da cui inizia la ricerca di primi di Sophie Germain;
	* @param args[1] -> maxprime, numero di primi da generare ad ogni iterazione di priming;
	* @param args[2] -> iterations, numero di iterazioni per il test di Solovay Strassen.
	*/	
	public Sieve(String[] args) {
		super(args, new Counter(new BigInteger(args[0])));
		setSophie(new BigInteger("5"));
		setOffset(Integer.parseInt(args[1]));
		setIterations(Integer.parseInt(args[2]));
		this.printAll();
		this.mainloop();
	}

//setters
	private void setIterations (int n) {
		this.iterations = n;
	}

	private void setOffset(int n) {
		this.offset = n;
	}

	private void setSophie(BigInteger p) {
		this.sophie = p;
	}

//getters
	public void print() {
		System.out.println("Founded Sophie Germain prime "+this.sophie);
	}

	public void printAll() {
		System.out.println("Sophie candidate: "+this.sophie);
		System.out.println("Offset for Filter generation: "+this.offset);
		System.out.println("Iterations for Solovay Strassen test: "+this.iterations);
	}

	public Token get() {
		Token T = new Token();
		T.setValue(this.sophie);
		return T;
	}

	public int iterations() {
		return this.iterations;
	}

	public boolean testloop(Token tok) {
		return (!solovayStrassen(tok.value(), this.iterations()));
	}

	public void mainloop() {
		Token tok;
		int i;

		do {
			//genero i filtri
			System.out.println("Sieve: generating filters");
			tok = next.get();
			super.set(new Filter(this.next, tok.value()));
			for (i=1; i<this.offset; i++) {
				tok = next.get();
				super.set(new Filter(this.next, tok.value()));
			}
			System.out.println("Sieve: last filter "+next.value());

			//cerco il primo di Sophie
			System.out.println("Sieve: looking for Sophie Germain prime");
			do {
				tok.setPriming(false);
				tok = next.get();
				this.setSophie(tok.value());
			} while(this.testloop(tok));
			System.out.println("Sieve: founded prime "+this.sophie);
			System.out.println(" ");
			tok.setPriming(true);
		} while(!solovayStrassen(this.sophie.multiply(new BigInteger("2")).add(BigInteger.ONE), iterations));

	}

	/**
	* @param candidate Numero da testare
	* @param iterations Numero di test da effettuare
	*/
	private boolean solovayStrassen(BigInteger candidate, int iterations) {
		boolean isPrime = true;
		int i,l;
		BigInteger a;

		for(i=0; i<iterations; i++) {
			a = pickRand(candidate);
			l = legendre(a,candidate);
			a = modularExponentiation(a, candidate.subtract(BigInteger.ONE).divide(new BigInteger("2")), candidate);
			//this is necessary to deal with results cong -1 mod candidate
			if (a.intValue() != 1 && a.intValue() != 0)
				a = a.subtract(candidate);
			if(l==0 || a.intValue()!=l) {
				isPrime = false;
				i = iterations;
			}
		}
		return(isPrime);
	}

	/*Bisognerebbe trovare un metodo più efficace per scegliere questi numeri senza ripetizioni*/
	/**
	* @param max Genera un biginteger casuale minore di max.
	*/
	public BigInteger pickRand (BigInteger max) {
		BigInteger s;
		do {
			s = new BigInteger(max.bitLength(), new Random());
		} while (s.compareTo(max)>=0 || s.compareTo(BigInteger.ZERO)==0);
		return(s);
	}
	
	/**
	* Metodo che calcola il simbolo di Legendre-Jacobi prendendo in input:
	* @param p Numero primo BigInteger;
	* @param q Numero primo dispari BigInteger.
	* @return n Simbolo di Legendre-Jacobi (p/q).
	*/
	public int legendre(BigInteger p, BigInteger q) {
		BigInteger tmp ;
		boolean positivity = true ;
		int n ;
	
		while(p.compareTo(BigInteger.ZERO) != 0 && p.compareTo(BigInteger.ONE) != 0) {
			
			if(p.mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) { //If 2|p, do p/2
				p = p.divide(new BigInteger("2")) ;
				if(q.mod(new BigInteger("8")).compareTo(new BigInteger("3")) == 0 || q.mod(new BigInteger("8")).compareTo(new BigInteger("5")) == 0) 
					positivity = !positivity ; //Sign unchanged if q = +1,-1 (mod 8)
			}else{
				if(p.compareTo(q) >= 0) //If p > q, do p (mod q)
					p = p.mod(q) ;
				else{ //If p < q, Quadratic Reciprocity
					tmp = p ;
					p = q ;
					q = tmp ;
					if(p.mod(new BigInteger("4")).compareTo(new BigInteger("3")) == 0 && q.mod(new BigInteger("4")).compareTo(new BigInteger("3")) == 0) 
						positivity = !positivity ; //Sign unchanged if p or q = 1 (mod 4)
				}
			}
		}
		if(p.compareTo(BigInteger.ZERO) == 0)
			n = 0 ;
		else{
			if(positivity == true)
				n = 1 ;
			else
				n = -1;
		}
		return n ;
	}

	/**
	 * @param base 
	 * @param exp esponente
	 * @param modulo
	 * @return esponenziazione modulare
	 */
	public BigInteger modularExponentiation(BigInteger base, BigInteger exp, BigInteger modulo) {
		BigInteger potenza; //(base)^esponente
		if(exp.compareTo(BigInteger.ZERO) == 0) //se l'esponente 0
			potenza = BigInteger.ONE; //ovviamente (base)^0=1 per ogni base
		else //qui inizia l'algoritmo
			potenza = power(base, exp, modulo);	
		return potenza;
	}

	public BigInteger power(BigInteger base, BigInteger exp, BigInteger modulo) {
		BigInteger potenza;
		int i;
		int length = exp.bitLength(); //lunghezza esponente in binario
		String bin = exp.toString(2); //esponente in binario
		BigInteger b = base;
		
		if(bin.charAt(length-1) == 49)
			potenza = base;
		else
			potenza = BigInteger.ONE;
		for(i=1; i<length; i++) {				
			b = b.pow(2).mod(modulo);
			if(bin.charAt(length-1-i) == 49)
				potenza = potenza.multiply(b).mod(modulo);
		}
		return potenza;
	}

}
