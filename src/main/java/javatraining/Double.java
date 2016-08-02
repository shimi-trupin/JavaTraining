package javatraining;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimi on 19/07/2016.
 */
public class Double<T extends Encryption>  extends EncryptionDecorator{

//    T first;
    T second ;

    public Double(T encryption) {
        super(encryption);
        throw new NotImplementedException();
    }
    public Double(T first , T second ){
        super (first);
        this.second=second;
    }
    // todo overrise supers encrypt and decrypt woth awsome logic much brains such wow.


    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
        EncryptionResult encryptionResult;
        List<Byte> keyList = new ArrayList<>();
        keyList.add(key.get(0));
        encryptionResult = encryption.encrypt(data, keyList);

        keyList.remove(0);
        keyList.add(key.get(1));

        data = encryptionResult.getData();
        encryptionResult = second.encrypt(data, keyList);

        encryptionResult.setKey(key);
        return encryptionResult;
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        List<Byte> keyList = new ArrayList<>();
        keyList.add(key.get(1));
        data = second.decrypt(data, keyList);

        keyList.remove(0);
        keyList.add(key.get(0));
        data = encryption.decrypt(data, keyList);

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
