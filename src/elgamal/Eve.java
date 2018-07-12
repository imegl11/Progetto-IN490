package elgamal;

import java.math.BigInteger;
import primes.sophie.Sieve;
import primes.sophie.Token;

public class Eve extends primes.Item<Token> {
	BigInteger q, g, y, x;
	public Eve(Bob B) {
		super(B);
		q = ((Alice)B.next).getPrime();
		g = ((Alice)B.next).getGenerator();
		y = B.value();
		x = shanks(q, g, y);
	}
	
	public BigInteger shanks(BigInteger q, BigInteger g, BigInteger y) {
		BigInteger m, gPower, z, i = BigInteger.ZERO;
		
		m = squareRoot(q.add(new BigInteger("-1")));
		pairs(q, g, m);
		while(i.compareTo(m) < 0) {
			gPower = ((Sieve)this.next.next.next).modularExponentiation(g, i, q).modInverse(q);
			z = y.multiply(gPower);
			if() {
				
			}
		}
	}
	
	public void pairs(BigInteger q, BigInteger g, BigInteger m) {
		BigInteger j = BigInteger.ZERO;
		while(j.compareTo(m) < 0) {
			
		}
	}
	
	BigInteger squareRoot(BigInteger x) {
		int i;
		BigInteger r, two = new BigInteger("2");
		r = x.divide(two);
	    for(i = 0; i < 30; i++)
	    	r = (r.add(x.divide(r))).divide(two);
	    return r;
	}
}
