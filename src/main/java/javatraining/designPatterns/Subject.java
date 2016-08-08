package javatraining.designPatterns;

/**
 * Created by shimi on 06/07/2016.
 */
public interface Subject {
    public void register(Observer observer);
    public void unregister(Observer observer);
    public void notifyObserver(String msg);

}
