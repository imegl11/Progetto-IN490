package elgamal;
import java.math.BigInteger;

public class CipherText {
	private BigInteger a, b;

	/**
	 * Costruttore di CipherText prende in input
	 * @param x 
	 * @param y
	 * coppia messaggio cifrato
	 */
	public CipherText(BigInteger x, BigInteger y) {
		System.out.println("Ciphertext creation");
		set1(x);
		set2(y);
		print();		
	}

//Setters
	
	private void set1(BigInteger n) {
		this.a = n;
	}
	private void set2(BigInteger n) {
		this.b = n;
	}
	
//Getters
	
	public BigInteger value1() {
		return this.a;
	}
	public BigInteger value2() {
		return this.b;
	}
	
	private void print() {
		System.out.println("Ciphertext: ("+this.a+", "+this.b+")");
		System.out.println(" ");
	}
}