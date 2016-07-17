package javatraining;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

/**
 * Created by shimi on 06/07/2016.
 */
public abstract class EncryptionDecorator implements Encryption{

    protected Encryption encryption;
    @Getter private File file;
    @Getter private Clock clock;
    @Getter private Date startDate;
    @Getter @Setter private long startTime;

    public EncryptionDecorator(Encryption encryption)
    {
        this.encryption = encryption;
//        this.startDate = System.currentTimeMillis();
//        this.startDate = Instant.now(clock);
//        Instant first = Instant.now();
//        Instant second = Instant.now();
//        this.startTime = System.currentTimeMillis();
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
