package digittal.signature.rsa;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class SHA1 {
  private static int BUFFER_SIZE = 32 * 1024; // kích thước buffer đọc tệp

  public static void main(String[] args) throws Exception {
    
  }

  public BigInteger md(String f) throws Exception {
    BufferedInputStream file = new BufferedInputStream(new FileInputStream(f));// Mở tệp để đọc
    
 // Khởi tạo đối tượng MessageDigest với thuật toán SHA-1
    MessageDigest md = MessageDigest.getInstance("SHA-1");
    
 // Tạo một DigestInputStream để tính toán giá trị băm của tệp
    DigestInputStream in = new DigestInputStream(file, md);
    int i;
    byte[] buffer = new byte[BUFFER_SIZE]; // Tạo một buffer để đọc tệp
    do {
      i = in.read(buffer, 0, BUFFER_SIZE); // Đọc tệp vào buffer
    } while (i == BUFFER_SIZE);  // Tiếp tục đọc cho đến khi không còn dữ liệu
    
 // Lấy đối tượng MessageDigest đã được cập nhật với dữ liệu từ tệp
    md = in.getMessageDigest();
    in.close();

    return new BigInteger(md.digest()); // Trả về giá trị băm của tệp dưới dạng BigInteger
  }
}