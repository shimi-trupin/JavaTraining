package javatraining;

import lombok.Cleanup;
import lombok.val;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
//            @Cleanup FileInputStream fileInputStream = new FileInputStream(file);
//            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(cypher);
//
//            byte b = 1;
//
//            while ((b = (byte) fileInputStream.read())!= -1)//read byte after byte until end of file
//            {
//                fileOutputStream.write(((b + key) % 256));
//            }

            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            for (int i=0; i<data.length; i++)//encrypt
            {
                data[i] = (byte) ((data[i] + key) % 256);
            }
//            File cypher = new File("C:\\Users\\shimi\\Desktop\\Untitled-5.jpg.encrypted");
            Files.write(cypher.toPath(), data);


            System.out.println("An encrypted file has been created!");
            System.out.println("Encrypted file location: " + cypher.getAbsolutePath());


            ////////////////////////// decryption //////////////////////

            File plain = new File("C:\\Users\\shimi\\Desktop\\Untitled-5_decrypted.jpg");
            for (int i=0; i<data.length; i++)
            {
                data[i] = (byte) ((data[i] - key) % 256);
            }
            Files.write(Paths.get("C:\\Users\\shimi\\Desktop\\Untitled-5_decrypted.jpg"), data);

            ////////////////////////////////////////////////////////////

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

    public static void enc(){
        Path path = Paths.get("C:\\Users\\shimi\\Desktop\\Untitled-5.jpg");

        byte key = randKey();
        System.out.println("Key is: " + key);

        try {
            byte[] data = Files.readAllBytes(path);
//            for (byte b:data) {
//                b = (byte) ((b + key) % 256);
//            }
            for (int i=0; i<data.length; i++)
            {
                data[i] = (byte) ((data[i] + key) % 256);
            }
            File cypher = new File("C:\\Users\\shimi\\Desktop\\Untitled-5.jpg.encrypted");
            Files.write(Paths.get("C:\\Users\\shimi\\Desktop\\Untitled-5.jpg.encrypted"), data);




            File plain = new File("C:\\Users\\shimi\\Desktop\\Untitled-5_decrypted.jpg");
            for (int i=0; i<data.length; i++)
            {
                data[i] = (byte) ((data[i] - key) % 256);
            }
            Files.write(Paths.get("C:\\Users\\shimi\\Desktop\\Untitled-5_decrypted.jpg"), data);
        }
        catch (IOException e)
        {
            System.out.println("IO Exception");
        }

    }
}
