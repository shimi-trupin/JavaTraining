package javatraining.algorithms;


import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;
import lombok.Getter;

import java.io.File;
import java.util.List;

/**
 * Created by shimi on 06/07/2016.
 */
public class Xor extends EncryptionDecorator {

//    @Getter private List<Byte> key;

    public Xor(/*Encryption encryption*/) {
        super();
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("XOR encryption started.");

//        this.key = key;

//        System.out.println("XOR: ");
        for (int i=0; i<data.length; i++)//encrypt
        {
            data[i] = (byte) (data[i] ^ /*this.*/key.get(0));
//            System.out.print(data[i] + " ");
        }

        notifyObserver("XOR encryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return new EncryptionResult(data, key);
    }


    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("XOR decryption started.");
        for (int i=0; i<data.length; i++)//write to file with decrypted bytes
        {
            data[i] = (byte) (data[i] ^ key.get(0));
        }

        notifyObserver("XOR decryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return data;

    }

}
