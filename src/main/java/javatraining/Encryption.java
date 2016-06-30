package javatraining;

import lombok.Cleanup;
import lombok.val;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by shimi on 29/06/2016.
 * Performs an encryption of file
 */
public class Encryption {

    public static void Encrypt(){

        FileOpener fileOpener = new FileOpener();
        File file = fileOpener.OpenFile(System.in, System.out);

        byte key = randKey();
        System.out.println("The encryption key is: " + key);

        File cypher = new File(file.getAbsolutePath() + ".encrypted");//create encrypted file
        try {
            @Cleanup FileInputStream fileInputStream = new FileInputStream(file);
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(cypher);

            byte b = 1;

            while ((b = (byte) fileInputStream.read())!= -1)//read byte after byte until end of file
            {
                fileOutputStream.write(((b + key) % 256));
            }
            System.out.println("An encrypted file has been created!");
            System.out.println("Encrypted file location: " + cypher.getAbsolutePath());

        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

    }

    /**
     * @return randomly generated number (byte)
     */
    public static byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }
}
