package javatraining;


import java.util.List;

/**
 * Created by shimi on 24/07/2016.todo add prints (notifications for observer)
 */
public class Reverse <T extends Encryption> extends EncryptionDecorator {
    public Reverse(T encryption) {
        super(encryption);
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
//        return super.encrypt(file);
        data = encryption.decrypt(data, key);
        return new EncryptionResult(data, key);
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        return encryption.encrypt(data, key).getData();
    }
}
