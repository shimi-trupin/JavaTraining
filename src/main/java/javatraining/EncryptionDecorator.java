package javatraining;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by shimi on 06/07/2016.
 */
public abstract class EncryptionDecorator implements Encryption, Subject{

    protected Encryption encryption;
    @Getter private File file;
    @Getter private Clock clock;
    @Getter private Date startDate;
    @Getter @Setter private long startTime;
    private ArrayList<Observer> observers;

    public EncryptionDecorator(Encryption encryption)
    {
        this.encryption = encryption;
        observers = new ArrayList<>();
//        this.startDate = System.currentTimeMillis();
//        this.startDate = Instant.now(clock);
//        Instant first = Instant.now();
//        Instant second = Instant.now();
//        this.startTime = System.currentTimeMillis();
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {
        encryption.encrypt(data, key);
//        this.file = file;

        return null;
    }

    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {
        encryption.decrypt(data, key);
//        this.file = file;
        return null;
    }

    public byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }

//    public File getFile(){
//        return file;
//    }

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
