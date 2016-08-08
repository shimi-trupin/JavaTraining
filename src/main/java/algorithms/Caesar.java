package algorithms;


import designPatterns.Encryption;
import designPatterns.EncryptionDecorator;
import tools.EncryptionResult;
import lombok.Getter;

import java.util.List;

/**
 * Caesar Algorithm
 * Created by shimi on 06/07/2016.
 */
public class Caesar extends EncryptionDecorator {


    @Getter private List<Byte> key;

    public Caesar(Encryption encryption) {
        super(encryption);
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("Caesar encryption started.");

        this.key = key;

            for (int i=0; i<data.length; i++)//encrypt
            {
                data[i] = (byte) ((data[i] + this.key.get(0)) % 256);
            }

            notifyObserver("Caesar encryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return new EncryptionResult(data, this.key);
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        setStartTime(System.currentTimeMillis());
        notifyObserver("Caesar decryption started.");
            for (int i=0; i<data.length; i++)//write to file with decrypted bytes
            {
                data[i] = (byte) ((data[i] - key.get(0)) % 256);
            }

            notifyObserver("Caesar decryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return data;
    }

}
