package javatraining.modules;

import com.google.inject.AbstractModule;
import javatraining.algorithms.Caesar;
import javatraining.designPatterns.Encryption;

/**
 * Created by shimi on 09/08/2016.
 */
public class CaesarModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Encryption.class).to(Caesar.class);
    }
}
