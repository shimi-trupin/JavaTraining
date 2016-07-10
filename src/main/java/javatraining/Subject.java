package javatraining;

/**
 * Created by shimi on 06/07/2016.
 */
public interface Subject {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver();

}
