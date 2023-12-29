package digittal.signature.rsa;

//package atnf.atoms.mon.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AlgorithmRSA {

    private BigInteger n, d, e;

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    // tạo một phiên bản có thể mã hóa bằng khóa công khai của người khác
    
    public AlgorithmRSA(BigInteger newn, BigInteger newe) {
        n = newn;
        e = newe;
    }

    // tạo 1 phiên bản có thể mã hóa và giải mã
    public AlgorithmRSA() {
        
       
    }
    
    /** hàm kiểm tra số nguyên tố
    public boolean isPrime(BigInteger x) {
        if (x.compareTo(BigInteger.valueOf(2)) < 0) return false;
        if (x.equals(BigInteger.valueOf(2))) return true;
        if (x.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return false;
        
        BigInteger sqrt = x.sqrt().add(BigInteger.ONE);
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(sqrt) < 0; i = i.add(BigInteger.valueOf(2))) {
            if (x.mod(i).equals(BigInteger.ZERO)) return false;
        }
        return true;
    }**/

    
    // tạo khóa
    public void KeyRSA(int bits){
        SecureRandom r = new SecureRandom(); // Tạo một đối tượng SecureRandom
        BigInteger p = new BigInteger(bits / 2, 100, r); // Tạo số nguyên tố p
        BigInteger q = new BigInteger(bits / 2, 100, r); // Tạo số nguyên tố q
        n = p.multiply(q); // Tính n = p*q
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // Tính hàm Euler φ(n) = (p-1)*(q-1)
        boolean found = false;
        do {
            e = new BigInteger(bits / 2, 50, r); // Tạo số e
            // Kiểm tra điều kiện gcd(e, φ(n)) = 1 và e < φ(n)
            if (m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {
                found = true;
            }
        } while (!found);
        d = e.modInverse(m); // Tính d sao cho (d*e) mod φ(n) = 1
    }

    // mã hóa RSA
    public synchronized String encrypt(String message) {
    	// chuyển đổi thông điệp thành dạng BigInterger bằng getBytes => phương thức modPow (byte^d mod n)
        return (new BigInteger(message.getBytes())).modPow(d, n).toString();
    }

   // mã hóa RSA với thông điệp dạng BigInteger
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(d, n);
    }

    // giải mã RSA
    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(e, n).toByteArray());
    }

    // giải mã RSA với thông điệp dạng BigInteger
    public synchronized BigInteger decrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // test
    public static void main(String[] args) throws Exception {

    }

    void setN(int bitleg) {
        throw new UnsupportedOperationException("Lỗi không hỗ trợ.");
    }
}
