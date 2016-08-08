package javatraining.designPatterns;

/**
 * Created by shimi on 17/07/2016.
 */
public class StartEndObserver implements Observer {
    @Override
    public void update(String msg) {
        System.out.println(msg);
    }
}
