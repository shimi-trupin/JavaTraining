package javatraining.tools;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by shimi on 23/07/2016.
 * Represents the result of an encryption.
 * Using this class, it is easy for an encrypt method to return the new encrypted file and it's key.
 */
public class EncryptionResult {

    @Getter @Setter private byte[] data;
//    @Getter @Setter private byte key;
    @Getter @Setter private List<Byte> key;

    public EncryptionResult(byte[] data, List<Byte> key) {
        this.data = data;
        this.key = key;
    }
}
