//SIMBOLO di LEGENDRE-JACOBI

class symbol {
	
	public static void main(String[] args) {
		legendreJacobi legendre ;
		
		System.out.println("p = "+args[0]) ;
		System.out.println("q = "+args[1]) ;
		System.out.println("Calcoliamo il simbolo di Legendre-Jacobi s = ("+args[0]+"/"+args[1]+")") ;
		
		legendre = new legendreJacobi(args) ;
		
		System.out.println("s = "+legendre.s) ;
		
		return ;
	}
}