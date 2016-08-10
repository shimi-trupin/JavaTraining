package javatraining.specialAlgorithms;

import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;
import lombok.Getter;
import lombok.Setter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimi on 19/07/2016.
 */
public class Double/*<T extends EncryptionDecorator>*/  extends EncryptionDecorator {

//    @Getter @Setter private byte[] data;
//    @Getter @Setter private List<Byte> key;
    private /*T*/EncryptionDecorator first;
    private /*T*/EncryptionDecorator second;

    public Double() {
        super(/*data, key*/);
        throw new NotImplementedException();
    }
    public Double(EncryptionDecorator first , EncryptionDecorator second){
        super();
        this.first = first;
        this.second=second;
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
        EncryptionResult encryptionResult;
        List<Byte> keyList = new ArrayList<>();
        keyList.add(key.get(0));
//        first.setKey(keyList);
        encryptionResult = first.encrypt(data, keyList);

        keyList.remove(0);
        keyList.add(key.get(1));
//        second.setKey(keyList);

        data = encryptionResult.getData();
        encryptionResult = second.encrypt(data, keyList);

        encryptionResult.setKey(key);
        return encryptionResult;
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        List<Byte> keyList = new ArrayList<>();
        keyList.add(key.get(1));
//        first.setKey(keyList);
        data = second.decrypt(data, keyList);

        keyList.remove(0);
        keyList.add(key.get(0));
//        second.setKey(keyList);
        data = first.decrypt(data, keyList);

        return data;
    }

    //    @Override
    /*public void encrypt(File file, byte key1, byte key2) {
        EncryptionResult encryptionResult;
        try {
            encryptionResult = encryption.encrypt(Files.readAllBytes(file.toPath()), KeyGen.randKey());
            String path = file.getParent() + "\\key.bin";
            // TODO: 25/07/2016 serialization
            Keys keys = new Keys(key1, key2);
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(keys);
            out.close();
            fileOut.close();
            *//*byte keys[] = {key1, key2};
            Files.write(Paths.get(path), keys);*//*

            path = file.getPath() + ".encrypted";
            Files.write(Paths.get(path), encryptionResult.getData());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

//        String path = encryptionResult.getFile().getParent() + "\\key.bin";
//        byte key1 = encryptionResult.getKey();

//        encryptionResult = second.encrypt(encryptionResult.getFile(), KeyGen.randKey());

//        byte key2 = encryptionResult.getKey();
//        byte keys[] = {key1, key2};
//        try {
//            Files.write(Paths.get(path), keys);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }*/



    /*@Override*/
    /*public void decrypt(File file, byte[] keys) {
//        super.decrypt(file, key);
        byte[] partiallyDecrypted;

        try {
            partiallyDecrypted = second.decrypt(Files.readAllBytes(file.toPath()), keys[1]);
            encryption.decrypt(partiallyDecrypted, keys[0]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

//        encryption.decrypt(partiallyDecrypted, keys[0]);
    }*/
}
