import com.fasterxml.jackson.core.JsonEncoding;

import java.io.File;
import java.util.Scanner;

/**
 * Created by shimi on 30/05/2016.
 * Chapter 1 exercise
 */
public class encryptor {
    public static void main(String[] args)
    {
        System.out.println("Type 'e' for encryption, or type 'd' for decryption");
        Scanner scanner = new Scanner(System.in);
        String src = scanner.nextLine();
        File file = new File(src);
        JsonEncoding a = null;
    }
}
