import java.math.BigInteger;
import elgamal.Alice;
import elgamal.Bob;
import elgamal.CipherText;
import elgamal.Eve;

public class Cryptosystem {
	
	public static void main(String[] args) {

		Alice A = new Alice(args);
		A.print();
		Bob B = new Bob(A);
		B.print();
		
		CipherText C = B.encryption(A);
		A.decryption(C);
		
		Eve E = new Eve(A);
		E.attack(C);
		E.print();
	}
}
