package primes.erathostenes.sophie;

import java.math.BigInteger;
import java.util.Random;

public class Sieve extends primes.erathostenes.Sieve {
	private BigInteger sophie;
	private boolean priming;
	private BigInteger prime;
	private BigInteger quantity;

//constructors
	/**
	* @param args[0] -> maxprime, numero di primi da generare ad ogni iterazione di priming;
	* @param args[1] -> bound, numero grande da cui inizia la ricerca di primi di Sophie Germain;
	* @param args[2] -> iterations, numero di iterazioni per il test di Solovay Strassen.
	*/	
	public Sieve(String[] args) {
		Counter C = new Counter(new BigInteger(args[1]));
		super.set(C);
		super.setmax(BigInteger.ZERO);
		setQuantity(BigInteger.ZERO);
		setSophie(BigInteger.ZERO);
		setPriming(true);
		int iterations = Integer.parseInt(args[2]);

		do {
			if (this.priming == true)
				super.mainloop();
			else
				this.mainloop(iterations);
		} while(!solovayStrassen(this.getPrime().multiply(new BigInteger("2")).add(BigInteger.ONE), iterations));
	}

//setters
	private void setSophie(BigInteger p) {
		this.sophie = p;
	}

	private void setQuantity(BigInteger q) {
		this.quantity=q;
	}

	private void setPriming(boolean b) {
		this.priming = b;
	}

	private void setCandidate(BigInteger p) {
		this.prime = p;
	}

	private void addQuantity() {
		this.quantity = this.quantity.add(BigInteger.ONE);
	}

//getters
	public BigInteger getPrime() {
		return this.prime;
	}

	public BigInteger getQuantity(BigInteger q) {
		return this.quantity;
	}

	private void mainloop(int iterations) {
		Token tok = next.get();
		while(!solovayStrassen(tok.value(), iterations))
			tok = next.get();
		this.setCandidate(tok.value());
	}

	/**
	* Usiamo la variabile euler del Sieve di Primes per controllare quanti numeri primi troviamo.
	* Per non sovrascrivere anche il super.mainloop siamo costretti a non cambiare l'interfaccia.
	*/
	private boolean testloop(Token token) {
		return (this.getmax().compareTo(super.value()) != 1);
	}

	/**
	* @param candidate Numero da testare
	* @param iterations Numero di test da effettuare
	*/
	public boolean solovayStrassen(BigInteger candidate, int iterations) {
		boolean isPrime = true;
		int i,l;
		BigInteger a;

		for(i=1; i<iterations; i++) {
			a = pickTest(candidate);
			l = legendre(a,candidate);
			a = modularExponentiation(a, candidate.subtract(BigInteger.ONE).divide(new BigInteger("2")), candidate);
			if(l==0 || a.remainder(candidate).intValue()!=l)
				isPrime = false;
		}
		return(isPrime);
	}

	/*Bisognerebbe trovare un metodo più efficace per scegliere questi numeri senza ripetizioni*/
	private BigInteger pickTest (BigInteger candidate) {
		BigInteger s;
		do {
			s = new BigInteger(candidate.bitLength(), new Random());
		} while (s.compareTo(candidate)>=0);
		return(s);
	}

	//dumb methods for compiling
	private BigInteger modularExponentiation(BigInteger b, BigInteger e, BigInteger m){
		return BigInteger.ZERO;
	}
	
	/**
	*Costruttore che calcola il simbolo di Legendre-Jacobi prendendo in input:
	* @param p Numero primo BigInteger
	* @param q Numero primo dispari BigInteger
	*/
	private int legendre(BigInteger p, BigInteger q) {
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

/* Da correggere!
	private BigInteger modularExponentiation(BigInteger base, BigInteger exp, BigInteger modulo) {
		int length;	//lunghezza esponente in binario
		BigInteger potenza; //(base)^esponente
		length=exp.bitLenght; //lunghezza esponente
		String bin; //stringa binaria dell'esponente da usare nell'algoritmo
		bin=exp.toString; //esponente in binario
		
		if(exp.compareTo(BigInteger.ZERO)==0) //se l'esponente ?0
		{
			potenza=BigInteger.ONE; //ovviamente (base)^0=1 per ogni base
		}
		else //qui inizia l'algoritmo
			potenza=power(base, exp, modulo, bin);	
			
		return potenza;
	}

	private BigInteger power(BigInteger base, BigInteger exp, BigInteger modulo, String bin) {
		BigInteger potenza=base;
			for(i=1; i<length; i++)
			{
				potenza=potenza.pow(2).mod(modulo);
				if(bin.charAt(i)==49)
				{
					potenza=potenza.multiply(base).mod(modulo);
				}
			}
			
		return potenza;
	}*/

}
