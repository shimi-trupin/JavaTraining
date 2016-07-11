package javatraining;


import lombok.Getter;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by shimi on 06/07/2016.
 */
public class Multiplication extends EncryptionDecorator{
    @Getter private byte key;

    public Multiplication(Encryption encryption) {
        super(encryption);
    }
//    private File file;

    @Override
    public void encrypt(File file) {

        do {
            key = super.randKey();
        } while (key%2 == 0);//make sure key is not divided by 2
        System.out.println("The encryption key is: " + key);

        File cypher = new File(file.getAbsolutePath() + ".encrypted");//create encrypted file

        try {

            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            for (int i=0; i<data.length; i++)//encrypt
            {
                data[i] = (byte) (data[i] * key);
            }
            Files.write(cypher.toPath(), data);


            System.out.println("An encrypted file has been created!");
            System.out.println("Encrypted file location: " + cypher.getAbsolutePath());


        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

    }

    @Override
    public void decrypt(File file, byte key) {
//        super.decrypt(file, key);
        byte decryptionKey = findDecryptionKey();

        try {
            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            String cypher = file.getAbsolutePath();

            String path = cypher.substring(0, cypher.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
            String file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
            file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

            File plain = new File(file_path);//create file
            for (int i=0; i<data.length; i++)//write to file with decrypted bytes
            {
                data[i] = (byte) (data[i] * decryptionKey);
            }
            Files.write(plain.toPath(), data);


        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

    }

    private byte findDecryptionKey()
    {
        int num;
        for (num = Byte.MIN_VALUE; num <= Byte.MAX_VALUE; num++ )
            if ((byte)((byte) num * key) == 1)
                break;
        return  (byte) num;
    }
}
