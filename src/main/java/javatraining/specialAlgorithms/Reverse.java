package javatraining.specialAlgorithms;


import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;

import java.util.List;

/**
 * Created by shimi on 24/07/2016.todo add prints (notifications for observer)
 */
public class Reverse /*<T extends Encryption>*/ extends EncryptionDecorator {

    private EncryptionDecorator algorithm;

    public Reverse(EncryptionDecorator algorithm) {
        super();
        this.algorithm = algorithm;
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
        data = algorithm.decrypt(data, key);
        return new EncryptionResult(data, key);
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        return algorithm.encrypt(data, key).getData();
    }
}
