package javatraining;

import java.io.File;
import java.util.Random;

/**
 * Created by shimi on 11/07/2016.
 */
public class EncryptionBase implements Encryption{

    @Override
    public EncryptionResult encrypt(File file) {
        //generate random key?
        return null;
    }

    @Override
    public File decrypt(File file, byte key) {

        return null;
    }

    protected byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }
}
