package javatraining;


import lombok.Getter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.ArrayList;

/**
 * Created by shimi on 06/07/2016.
 */
public class Caesar extends EncryptionDecorator /*implements Subject*/{

//    private ArrayList<Observer> observers;
    @Getter private byte key;

    public Caesar(Encryption encryption) {
        super(encryption);
//        observers = new ArrayList<>();
    }
//    private File file;

    @Override
    public EncryptionResult encrypt(File file) {
//        super.encrypt(file);

        setStartTime(System.currentTimeMillis());
        notifyObserver("Caesar encryption started.");
        File cypher;

        key = super.randKey();
        System.out.println("The encryption key is: " + key);
        if(!file.getAbsolutePath().endsWith(".encrypted")) {
            cypher = new File(file.getAbsolutePath() + ".encrypted");//create encrypted file
        }
        else cypher = file;

        try {

            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            for (int i=0; i<data.length; i++)//encrypt
            {
                data[i] = (byte) ((data[i] + key) % 256);
            }
//            File cypher = new File("C:\\Users\\shimi\\Desktop\\Untitled-5.jpg.encrypted");
            Files.write(cypher.toPath(), data);


            System.out.println("An encrypted file has been created!");
            System.out.println("Encrypted file location: " + cypher.getAbsolutePath());

            notifyObserver("Caesar encryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return new EncryptionResult(cypher, key);

        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

//        this.file = super.getFile();
        return null;
    }

    @Override
    public File decrypt(File file, byte key) {
//        super.decrypt(file, key);
        setStartTime(System.currentTimeMillis());
        notifyObserver("Caesar decryption started.");

        try {
            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            String cypher = file.getAbsolutePath();
            File plain;

            if(cypher.endsWith(".encrypted")) {
                String path = cypher.substring(0, cypher.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                String file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                plain = new File(file_path);//create file
            }
            else plain = file;

            for (int i=0; i<data.length; i++)//write to file with decrypted bytes
            {
                data[i] = (byte) ((data[i] - key) % 256);
            }
            Files.write(plain.toPath(), data);

            notifyObserver("Caesar decryption ended.\nTime took: "
                    + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

            return plain;
        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }
        return null;
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
    public void notifyObserver(String msg) {
        for (Observer observer: observers)
            observer.update(msg);

    }*/


}
