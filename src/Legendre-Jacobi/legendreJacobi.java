import java.math.BigInteger;

class legendreJacobi {
	BigInteger p ;
	BigInteger q ;
	int s ;
	boolean positivity ;
	

	
	legendreJacobi(String[] args) {
		this.p = new BigInteger(args[0]) ;
		this.q = new BigInteger(args[1]) ;
		
		this.s = calculus() ;
	}
	
	int calculus() {
		BigInteger tmp ;
		this.positivity = true ;
		int n ;
	
		while(this.p.compareTo(BigInteger.ZERO) != 0 && this.p.compareTo(BigInteger.ONE) != 0) {
			
			if(this.p.mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) { //Se 2|p, faccio p = p/2
				//Segno: positivo se q = +1,-1 (mod 8), negativo se q = +3,-3 (mod 8)
				this.p = this.p.divide(new BigInteger("2")) ;
				if(this.q.mod(new BigInteger("8")).compareTo(new BigInteger("3")) == 0 || this.q.mod(new BigInteger("8")).compareTo(new BigInteger("5")) == 0)
					this.positivity = !this.positivity ;
				
				System.out.println("p = p/2 = "+this.p+"; "+this.positivity) ;
			
			}else{
				
				if(this.p.compareTo(this.q) >= 0) { //Se p > q, p diventa p modulo q
					this.p = this.p.mod(this.q) ;
				
					System.out.println("p = "+this.p+" (mod "+this.q+"); "+this.positivity) ;
				
				}else{ //Se p < q, scambio p e q
					//Segno: positivo se p = 1 oppure q = 1 (mod 4), altrimenti negativo se p = q = 3 (mod 4)
					tmp = this.p ;
					this.p = this.q ;
					this.q = tmp ;
					if(this.p.mod(new BigInteger("4")).compareTo(new BigInteger("3")) == 0 && this.q.mod(new BigInteger("4")).compareTo(new BigInteger("3")) == 0)
						this.positivity = !this.positivity ;
				
					System.out.println("Change: p = "+this.p+" e q = "+this.q+"; "+this.positivity) ;
				}
			}
		}

		if(this.p.compareTo(BigInteger.ZERO) == 0) //Se p = 0, allora n = 0
			n = 0 ;
		else{
			if(this.positivity == false)
				n = -1 ;
			else
				n = 1 ;
		}
		return n ;
	}
}