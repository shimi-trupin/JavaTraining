package javatraining;

import java.io.File;

/**
 * Created by shimi on 06/07/2016.
 */
public interface Encryption {
    public EncryptionResult encrypt(File file);
    public File decrypt(File file, byte key);
}
