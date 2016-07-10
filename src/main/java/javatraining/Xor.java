package javatraining;


import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by shimi on 06/07/2016.
 */
public class Xor extends EncryptionDecorator implements Subject{

    private ArrayList<Observer> observers;
    private byte key;
//    private File file;

    @Override
    public void encrypt(File file) {
        super.encrypt(file);

        key = super.randKey();
        System.out.println("The encryption key is: " + key);

        File cypher = new File(file.getAbsolutePath() + ".encrypted");//create encrypted file

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


        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }

//        this.file = super.getFile();
    }


    @Override
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

    }


}
