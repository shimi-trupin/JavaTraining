package javatraining;

import java.time.Clock;

/**
 * Created by shimi on 06/07/2016.
 * http://tempusfugitlibrary.org/documentation/time/clocks/
 */
public interface Observer {
    public void update(Clock clock);
}
