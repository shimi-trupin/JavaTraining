package javatraining;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Random;

/**
 * Created by shimi on 06/07/2016.
 */
public abstract class EncryptionDecorator implements Encryption{

    protected Encryption encryption;
    @Getter private File file;

    public EncryptionDecorator(Encryption encryption)
    {
        this.encryption = encryption;
    }

    @Override
    public void encrypt(File file) {
        encryption.encrypt(file);
//        this.file = file;

    }

    @Override
    public void decrypt(File file, byte key) {
        encryption.decrypt(file, key);
//        this.file = file;
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
