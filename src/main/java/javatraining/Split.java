package javatraining;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimi on 25/07/2016.
 */
public class Split<T extends Encryption> extends EncryptionDecorator{
    public Split(Encryption encryption) {
        super(encryption);
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
//        try {
//            byte[] data = Files.readAllBytes(file.toPath());
            byte odd[], even[];

            //split data into odd and even
            if(data.length%2 ==0) {
                odd = new byte[data.length / 2];
                even = new byte[data.length / 2];
            }
            else {
                odd = new byte[data.length / 2];
                even = new byte[(data.length / 2) + 1];
            }
            for(int i=0, j=0, k=0; i<data.length; i++)
            {
                if(i%2 != 0)
                    odd[j++] = data[i];
                else
                    even[k++] = data[i];
            }

            List<Byte> keyList = new ArrayList<>();

            //encrypt odd bytes with key1
            keyList.add(key.get(0));
            EncryptionResult encryptionResult = encryption.encrypt(odd, keyList);
            odd = encryptionResult.getData();

            //encrypt even bytes with key2
            keyList.remove(0);
            keyList.add(key.get(1));
            encryptionResult = encryption.encrypt(even, keyList);
            even = encryptionResult.getData();

            //merge odd and even bytes together
            for (int i=0, j=0; i< odd.length; i++)
            {
                data[j++] = even[i];
                data[j++] = odd[i];
            }
            if (data.length % 2 != 0)
                data[data.length-1] = even[even.length-1];

            /*//write "key.bin" file (with serialization
            String path = file.getParent() + "\\key.bin";
            // TODO: 25/07/2016 serialization
            Keys keys = new Keys(key1, key2);
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(keys);
            out.close();
            fileOut.close();
            ///////
            *//*byte keys[] = {key1, key2};
            Files.write(Paths.get(path), keys);*//*

            //write '.encrypted' file
            path = file.getPath() + ".encrypted";
            Files.write(Paths.get(path), data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
        return new EncryptionResult(data, key);
    }

    /*public void encrypt(File file, byte key1, byte key2) {

        try {
            byte[] data = Files.readAllBytes(file.toPath());
            byte odd[], even[];

            //split data into odd and even
            if(data.length%2 ==0) {
                odd = new byte[data.length / 2];
                even = new byte[data.length / 2];
            }
            else {
                odd = new byte[data.length / 2];
                even = new byte[(data.length / 2) + 1];
            }
            for(int i=0, j=0, k=0; i<data.length; i++)
            {
                if(i%2 != 0)
                    odd[j++] = data[i];
                else
                    even[k++] = data[i];
            }

            //encrypt odd bytes with key1
            EncryptionResult encryptionResult = encryption.encrypt(odd, key1);
            odd = encryptionResult.getData();

            //encrypt even bytes with key2
            encryptionResult = encryption.encrypt(even, key2);
            even = encryptionResult.getData();

            //merge odd and even bytes together
            for (int i=0, j=0; i< odd.length; i++)
            {
                data[j++] = even[i];
                data[j++] = odd[i];
            }
            if (data.length % 2 != 0)
                data[data.length-1] = even[even.length-1];

            //write "key.bin" file (with serialization
            String path = file.getParent() + "\\key.bin";
            // TODO: 25/07/2016 serialization
            Keys keys = new Keys(key1, key2);
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(keys);
            out.close();
            fileOut.close();
            ///////
            *//*byte keys[] = {key1, key2};
            Files.write(Paths.get(path), keys);*//*

            //write '.encrypted' file
            path = file.getPath() + ".encrypted";
            Files.write(Paths.get(path), data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }*/


    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        byte odd[], even[];

        //split data into odd and even
        if(data.length%2 ==0) {
            odd = new byte[data.length / 2];
            even = new byte[data.length / 2];
        }
        else {
            odd = new byte[data.length / 2];
            even = new byte[(data.length / 2) + 1];
        }
        for(int i=0, j=0, k=0; i<data.length; i++)
        {
            if(i%2 != 0)
                odd[j++] = data[i];
            else
                even[k++] = data[i];
        }

        List<Byte> keyList = new ArrayList<>();

        //decrypt odd bytes with key1
        keyList.add(key.get(0));
        odd = encryption.decrypt(odd, keyList);

        //encrypt even bytes with key2
        keyList.remove(0);
        keyList.add(key.get(1));
        even = encryption.decrypt(even, keyList);

        //merge odd and even bytes together
        for (int i=0, j=0; i< odd.length; i++)
        {
            data[j++] = even[i];
            data[j++] = odd[i];
        }
        if (data.length % 2 != 0)
            data[data.length-1] = even[even.length-1];

        return data;
    }

    /*public byte[] decrypt(byte[] data, Keys keys) {

        byte odd[], even[];

        //split data into odd and even
        if(data.length%2 ==0) {
            odd = new byte[data.length / 2];
            even = new byte[data.length / 2];
        }
        else {
            odd = new byte[data.length / 2];
            even = new byte[(data.length / 2) + 1];
        }
        for(int i=0, j=0, k=0; i<data.length; i++)
        {
            if(i%2 != 0)
                odd[j++] = data[i];
            else
                even[k++] = data[i];
        }

        //decrypt odd bytes with key1
        odd = encryption.decrypt(odd, keys.getKey1());

        //encrypt even bytes with key2
        even = encryption.decrypt(even, keys.getKey2());

        //merge odd and even bytes together
        for (int i=0, j=0; i< odd.length; i++)
        {
            data[j++] = even[i];
            data[j++] = odd[i];
        }
        if (data.length % 2 != 0)
            data[data.length-1] = even[even.length-1];

        return data;
    }*/
}
