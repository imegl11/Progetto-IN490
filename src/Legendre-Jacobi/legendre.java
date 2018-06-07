import java.math.BigInteger;

class legendre {
	BigInteger p ;
	BigInteger q ;
	int n ;
	boolean positivity ;
	

	
	legendre(String[] args) {
		this.p = new BigInteger(args[0]) ;
		this.q = new BigInteger(args[1]) ;
		
		this.n = Calcolo() ;
	}
	
	int Calcolo() {
		BigInteger tmp ;
		this.positivity = true ;
	
		while(this.p.compareTo(BigInteger.ZERO) != 0 && this.p.compareTo(BigInteger.ONE) != 0 && this.p.compareTo(new BigInteger("2")) != 0) {
			
			if(this.p.compareTo(this.q) >= 0) { //Se p > q, p diventa p modulo q
				this.p = this.p.mod(this.q) ;
				System.out.println("p (modulo q) = "+this.p) ;
			}else{
				//(Se p < q) Se p oppure q sono congrui ad 1 modulo 4, allora scambio p e q
				//(Se p < q) Se p e q sono congrui ad 3 modulo 4, allora metto un - davanti e scambio p e q 
				tmp = this.p ;
				this.p = this.q ;
				this.q = tmp ;
				System.out.println("Scambio p e q: p = "+this.p+" e q = "+this.q) ;
			
				if(this.p.mod(new BigInteger("4")).compareTo(new BigInteger("3")) == 0 && this.q.mod(new BigInteger("4")).compareTo(new BigInteger("3")) == 0)
					this.positivity = !this.positivity ; //this.positivity = false ;
				
				System.out.println("Positivity = "+this.positivity) ;
			}
		}
		System.out.println("Positivity = "+this.positivity) ;
		
		if(this.p.compareTo(BigInteger.ZERO) == 0) //Se p = 0, ovvero p iniziale = q, allora n = 0
			return 0;
		else{
			//Se p = 2, allora n = 1 (se q = +1,-1 mod 8) oppure n = -1
			if(this.p.compareTo(new BigInteger("2")) == 0) { //Se p = 1, allora n = 1
				if(this.q.mod(new BigInteger("8")).compareTo(new BigInteger("3")) == 0 || this.q.mod(new BigInteger("8")).compareTo(new BigInteger("5")) == 0)
					this.positivity = !this.positivity ;
			}
			if(this.positivity == false)
				return -1;
			else
				return 1;
		}
	}
}