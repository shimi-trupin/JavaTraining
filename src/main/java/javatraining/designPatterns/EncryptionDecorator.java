package javatraining.designPatterns;

import com.google.inject.Inject;
import javatraining.tools.EncryptionResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shimi on 06/07/2016.
 */
public abstract class EncryptionDecorator implements Encryption, Subject{

//    protected Encryption encryption;
//    @Getter @Setter private byte[] data;
//    @Getter @Setter private List<Byte> key;
    @Getter @Setter private long startTime;
    private ArrayList<Observer> observers;

    @Inject
    public EncryptionDecorator(/*byte[] data, List<Byte> key*/)
    {
//        this.data = data;
//        this.key = key;
//        this.encryption = encryption;
        observers = new ArrayList<>();
    }

    @Override
    public abstract EncryptionResult encrypt(byte[] data, List<Byte> key);

    @Override
    public abstract byte[] decrypt(byte[] data, List<Byte> key);

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
