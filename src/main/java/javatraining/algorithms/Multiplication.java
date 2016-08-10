package javatraining.algorithms;


import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;
import lombok.Getter;

import java.io.File;
import java.util.List;

/**
 * Created by shimi on 06/07/2016.
 * Multiplication Algorithm.
 */
public class Multiplication extends EncryptionDecorator {
//    @Getter private List<Byte> key;

    public Multiplication() {
        super(/*encryption*/);
    }
//    private File file;

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("Multiplication encryption started.");

        for (int i=0; i<data.length; i++)//encrypt
        {
            data[i] = (byte) ((data[i] * key.get(0)) % 256);
        }

        notifyObserver("Multiplication encryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return new EncryptionResult(data, key);
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("Multiplication decryption started.");

        byte decryptionKey = findDecryptionKey(key.get(0));

        for (int i=0; i<data.length; i++)//write to file with decrypted bytes
        {
            data[i] = (byte) ((data[i] * decryptionKey) % 256);
        }

        notifyObserver("Multiplication decryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return data;
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
