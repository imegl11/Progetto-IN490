package primes.sophie;

import java.math.BigInteger;
import primes.erathostenes;

public class Sieve extends erathostenes.Sieve {
	BigInteger sophie;

//constructors
	//il counter non genererà altri primi perché sono tutti maggiori di maxprime, invece dovrebbe generarne un po'
	//bisogna far collassare tutti questi cicli nel main loop, sfruttando la modalità priming del token
	public Sieve(String[] args) {
		super(args, new Counter(new BigInteger(args[1])));
		setSophie(BigInteger.ZERO);

		do {
			super.mainloop();
			do
				Token token = this.next.get();
			while(!solovayStrassen(token.value(), parseInt(args[2])))
		} while(!solovayStrassen(token.value().multiply(new BigInteger("2").add(BigInteger.ONE)), parseInt(args[2])))
		this.print();
	}

//setters
	private void setSophie(BigInteger p) {
		this.sophie = p;
	}

//getters
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
			a = modularExponentiation(a, candidate.subtract(BigInteger.ONE).divide(new BigInteger("2")), candidate)
			if(x==0 || a.remainder(candidate).compareTo(x)!=0)
				isPrime = false;
		}
		return(isPrime);
	}

	/*Bisognerebbe trovare un metodo più efficace per scegliere questi numeri senza ripetizioni*/
	private BigInteger pickTest (BigInteger candidate) {
		BigInteger s;
		do {
			s = new BigInteger(candidate.bitLength(), new Random())
		} while (s.compareTo(n)>=0);
		return(s);
	}

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
	}

}
