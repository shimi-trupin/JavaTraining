package javatraining;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by shimi on 19/07/2016.
 */
public class Double<T extends Encryption>  extends EncryptionDecorator{

//    T first;
    T second ;

    public Double(T encryption) {
        super(encryption);
        throw new NotImplementedException();
    }
    public Double(T first , T second ){
        super (first);
        this.second=second;
    }
    // todo overrise supers encrypt and decrypt woth awsome logic much brains such wow.

    @Override
    public EncryptionResult encrypt(File file) {
        EncryptionResult encryptionResult;
        encryptionResult = encryption.encrypt(file);

        String path = encryptionResult.getFile().getParent() + "\\key.bin";
        byte key1 = encryptionResult.getKey();

        encryptionResult = second.encrypt(encryptionResult.getFile());

        byte key2 = encryptionResult.getKey();
        byte keys[] = {key1, key2};
        try {
            Files.write(Paths.get(path), keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*@Override*/
    public void decrypt(File file, byte[] keys) {
//        super.decrypt(file, key);
        File partiallyDecrypted;

        partiallyDecrypted = second.decrypt(file, keys[1]);

        encryption.decrypt(partiallyDecrypted, keys[0]);
    }
}
