package javatraining;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Created by shimi on 23/07/2016.
 * Represents the result of an encryption.
 * Using this class, it is easy for an encrypt method to return the new encrypted file and it's key.
 */
public class EncryptionResult {

    @Getter @Setter private File file;
    @Getter @Setter private byte key;

    public EncryptionResult(File file, byte key) {
        this.file = file;
        this.key = key;
    }
}
