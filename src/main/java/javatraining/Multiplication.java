package javatraining;


import lombok.Getter;

import java.io.File;
import java.nio.file.Files;

/**
 * Created by shimi on 06/07/2016.
 * Multiplication Algorithm.
 */
public class Multiplication extends EncryptionDecorator{
    @Getter private byte key;

    public Multiplication(Encryption encryption) {
        super(encryption);
    }
//    private File file;

    @Override
    public EncryptionResult encrypt(File file) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("Multiplication encryption started.");
        File cypher;

        do {
            key = super.randKey();
        } while (key%2 == 0);//make sure key is not divided by 2
        System.out.println("The encryption key is: " + key);

        if(!file.getAbsolutePath().endsWith(".encrypted")) {
            cypher = new File(file.getAbsolutePath() + ".encrypted");//create encrypted file
        }
        else cypher = file;

        try {

            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            /*////////////
            System.out.println("Before enc: "+ data[0]);
            ////////////*/
            for (int i=0; i<data.length; i++)//encrypt
            {
                data[i] = (byte) ((data[i] * key) % 256);
            }
           /* ////////////
            System.out.println("After enc: "+ data[0]);
            ////////////*/
            Files.write(cypher.toPath(), data);


            System.out.println("An encrypted file has been created!");
            System.out.println("Encrypted file location: " + cypher.getAbsolutePath());

            notifyObserver("Multiplication encryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return new EncryptionResult(cypher, key);
        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

        return null;
    }

    @Override
    public File decrypt(File file, byte key) {
//        super.decrypt(file, key);
        setStartTime(System.currentTimeMillis());
        notifyObserver("Multiplication decryption started.");

        byte decryptionKey = findDecryptionKey(key);

        try {
            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            String cypher = file.getAbsolutePath();
            File plain;

            if(cypher.endsWith(".encrypted")) {
                String path = cypher.substring(0, cypher.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                String file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                plain = new File(file_path);//create file
            }
            else plain = file;
            /*////////////
            System.out.println("Before dec: "+ data[0]);
            System.out.println("DK = " + decryptionKey);
            ////////////*/
            for (int i=0; i<data.length; i++)//write to file with decrypted bytes
            {
                data[i] = (byte) ((data[i] * decryptionKey) % 256);
            }
            /*////////////
            System.out.println("After dec: "+ data[0]);
            ////////////*/
            Files.write(plain.toPath(), data);

            notifyObserver("Multiplication decryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return plain;
        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

        return null;
    }

    private byte findDecryptionKey(byte key)
    {
        int num;
        for (num = Byte.MIN_VALUE; num <= Byte.MAX_VALUE; num++ )
            if ((byte)((num * key) % 256) == 1)
                break;
        return  (byte) num;
    }
}
