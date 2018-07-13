package elgamal;
import java.math.BigInteger;

public class CipherText {
	private BigInteger a, b;

//constructors
	public CipherText(BigInteger x, BigInteger y) {
		set1(x);
		set2(y);
		print();		
	}

// setters
	private void set1(BigInteger n) {
		this.a = n;
	}
	private void set2(BigInteger n) {
		this.b = n;
	}
	
// getters
	public BigInteger value1() {
		return this.a;
	}
	public BigInteger value2() {
		return this.b;
	}
	
	private void print() {
		System.out.println("Ciphertext: (c1, c2) = ("+this.a+", "+this.b+")");
	}
	
}