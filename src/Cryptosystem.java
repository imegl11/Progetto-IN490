import java.math.BigInteger;
import elgamal.Alice;
import elgamal.Bob;

public class Cryptosystem {
	
	public static void main(String[] args) {
		Alice A = new Alice(args);
		Bob B = new Bob(A, new BigInteger(args[3]));
		return ;
	}
	
}

