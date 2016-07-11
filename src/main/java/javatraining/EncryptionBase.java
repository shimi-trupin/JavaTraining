package javatraining;

import java.io.File;
import java.util.Random;

/**
 * Created by shimi on 11/07/2016.
 */
public class EncryptionBase implements Encryption{

    @Override
    public void encrypt(File file) {
        //generate random key?
    }

    @Override
    public void decrypt(File file, byte key) {

    }

    protected byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }
}
