package javatraining;


import lombok.Getter;

import java.io.File;
import java.util.List;

/**
 * Created by shimi on 06/07/2016.
 */
public class Xor extends EncryptionDecorator /*implements Subject*/{

//    private ArrayList<Observer> observers;
    @Getter private List<Byte> key;

    public Xor(Encryption encryption) {
        super(encryption);
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
//        super.encrypt(file);

        setStartTime(System.currentTimeMillis());
        notifyObserver("XOR encryption started.");
        File cypher;

//        this.key = super.randKey();
        this.key = key;

        System.out.println("The encryption key is: " + this.key);

        /*if(!data.getAbsolutePath().endsWith(".encrypted")) {
            cypher = new File(data.getAbsolutePath() + ".encrypted");//create encrypted file
        }
        else cypher = data;

        try {

            byte[] data = Files.readAllBytes(data.toPath());//file to bytes*/

            for (int i=0; i<data.length; i++)//encrypt
            {
                data[i] = (byte) (data[i] ^ this.key.get(0));
            }
//            Files.write(cypher.toPath(), data);


//            System.out.println("An encrypted file has been created!");
//            System.out.println("Encrypted file location: " + cypher.getAbsolutePath());

            notifyObserver("XOR encryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return new EncryptionResult(data, this.key);
        /*}
        catch (Exception e) {
            System.out.println("Could not write file");
        }
        return null;*/
    }


    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
//        super.decrypt(file, key);
        setStartTime(System.currentTimeMillis());
        notifyObserver("XOR decryption started.");

        /*try {
            byte[] data = Files.readAllBytes(data.toPath());//file to bytes

            String cypher = data.getAbsolutePath();

            File plain;

            if(cypher.endsWith(".encrypted")) {
                String path = cypher.substring(0, cypher.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                String file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                plain = new File(file_path);//create file
            }
            else plain = data;*/

            for (int i=0; i<data.length; i++)//write to file with decrypted bytes
            {
                data[i] = (byte) (data[i] ^ key.get(0));
            }
//            Files.write(plain.toPath(), data);

            notifyObserver("XOR decryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return data;
        /*}
        catch (Exception e) {
            System.out.println("Could not write file");
        }
        return null;*/
    }

    /*@Override
    public void register(Observer newObserver) {
        // Adds a new observer to the ArrayList
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {

        // Get the index of the observer to delete
        int observerIndex = observers.indexOf(deleteObserver);

        // Print out message (Have to increment index to match)
        System.out.println("Observer " + (observerIndex+1) + " deleted");

        // Removes observer from the ArrayList
        observers.remove(observerIndex);

    }

    @Override
    public void notifyObserver() {

    }*/


}
