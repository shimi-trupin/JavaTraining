package javatraining.designPatterns;

import javatraining.tools.EncryptionResult;

import java.util.List;

/**
 * Created by shimi on 06/07/2016.
 */
public interface Encryption {
    public EncryptionResult encrypt(byte[] data, List<Byte> key);
    public byte[] decrypt(byte[] data, List<Byte> key);
}
