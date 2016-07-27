package javatraining;

/**
 * Created by shimi on 06/07/2016.
 */
public interface Encryption {
    public EncryptionResult encrypt(byte[] data, byte key);
    public byte[] decrypt(byte[] data, byte key);
}
