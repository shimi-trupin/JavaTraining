package designPatterns;

import tools.EncryptionResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shimi on 06/07/2016.
 */
public abstract class EncryptionDecorator implements Encryption, Subject{

    protected Encryption encryption;
    @Getter @Setter private long startTime;
    private ArrayList<Observer> observers;

    public EncryptionDecorator(Encryption encryption)
    {
        this.encryption = encryption;
        observers = new ArrayList<>();
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
        encryption.encrypt(data, key);
        return null;
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        encryption.decrypt(data, key);
        return null;
    }

    public byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
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
    public void notifyObserver(String msg) {
        for (Observer observer: observers)
            observer.update(msg);

    }

}
