package javatraining;

import java.io.File;

/**
 * Created by shimi on 06/07/2016.
 */
public interface Encryption {
    public void encrypt(File file);
    public void decrypt(File file, Byte key);
}
