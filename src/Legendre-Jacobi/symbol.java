//SIMBOLO di LEGENDRE

/* Il simbolo di Jacobi è l'analogo del simbolo di Legendre nel caso di numeri non primi, quindi nel nostro caso possiamo considerare esclusivamente quello di Legendre */

class symbol {
	
	public static void main(String[] args) {
		legendre legendre ;
		
		System.out.println("p = "+args[0]) ;
		System.out.println("q = "+args[1]) ;
		System.out.println("Calcoliamo il simbolo di Legendre n = ("+args[0]+"/"+args[1]+")") ;
		
		legendre = new Legendre(args) ;
		
		System.out.println("n = "+legendre.n) ;
		
		return ;
	}
}