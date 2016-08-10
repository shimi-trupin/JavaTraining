package javatraining.algorithms;


import com.google.inject.Inject;
import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Caesar Algorithm
 * Created by shimi on 06/07/2016.
 */
public class Caesar extends EncryptionDecorator {

/*    @Getter @Setter private byte[] data;
    @Getter @Setter private List<Byte> key;*/

    @Inject
    public Caesar() {
        super();
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("Caesar encryption started.");

//        this.key = key;

//        System.out.println("Caesar: ");
        for (int i=0; i<data.length; i++)//encrypt
        {
            data[i] = (byte) ((data[i] + key.get(0)) % 256);
//            System.out.print(data[i] + " ");
        }

        notifyObserver("Caesar encryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return new EncryptionResult(data, key);
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
