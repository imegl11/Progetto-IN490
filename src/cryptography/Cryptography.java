package cryptography;

import java.math.BigInteger;

/*In attesa di capire dove è meglio mettere questi metodi, definiamo un'interfaccia*/

public interface Cryptography {

	/*Candidate è il presunto primo, iterations è un bound per il numero di test effettuati.*/

	public boolean solovayStrassen (BigInteger candidate, int iterations) {
		boolean isPrime = true;
		int i,l;
		BigInteger a;

		for(i=1; i<iterations; i++) {
			a = pickTest(candidate);
			l = legendre(a,candidate);
			a = modularExponentiation(a, candidate.subtract(BigInteger.ONE).divide(new BigInteger "2"), candidate)
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
}