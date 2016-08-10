package javatraining.modules;

import com.google.inject.AbstractModule;
import javatraining.designPatterns.Encryption;

/**
 * Created by shimi on 08/08/2016.
 */
public class EncryptionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Encryption.class).to(Encryption.class);
    }
}
