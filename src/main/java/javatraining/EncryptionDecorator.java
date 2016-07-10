package javatraining;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Random;

/**
 * Created by shimi on 06/07/2016.
 */
public abstract class EncryptionDecorator implements Encryption{

    @Getter private File file;

    @Override
    public void encrypt(File file) {

        this.file = file;

    }

    @Override
    public void decrypt(File file, Byte key) {
        this.file = file;
    }

    public byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }

//    public File getFile(){
//        return file;
//    }
}
