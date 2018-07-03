/** implementazione del crivello di ertaostene
* @author Beatrice Beco, Daniele Salierno, Lucio Zaccardelli
**/

import primes.erathostenes.sophie.Sieve ;

public class Primi {
	public static void main(String[] args) {
		Sieve S ;
		
		S = new Sieve(args);
		S.print();
		return;
	}
	
}