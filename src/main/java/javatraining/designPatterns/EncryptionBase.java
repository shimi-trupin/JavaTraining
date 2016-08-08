package javatraining.designPatterns;

import javatraining.tools.EncryptionResult;

import java.util.List;
import java.util.Random;

/**
 * Created by shimi on 11/07/2016.
 */
public class EncryptionBase implements Encryption{

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
        //generate random key?
        return null;
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {

        return null;
    }

    protected byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }
}
