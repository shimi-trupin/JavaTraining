package javatraining.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.KeyGen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shimi on 10/08/2016.
 */
public class AsyncDirModule extends AbstractModule {// TODO: 10/08/2016 delete this file?

    private File file;
    List<Byte> key;
    EncryptionDecorator algorithm;

    public AsyncDirModule(File file, EncryptionDecorator algorithm ,List<Byte> key) {
        this.file = file;
        this.algorithm = algorithm;
        this.key = key;
    }

    @Override
    protected void configure() {

        System.out.println("Type 'E' for encryption, or type 'D' for decryption");
        Scanner scanner = new Scanner(System.in);// TODO: 10/08/2016 injection?
        String encrypt_decrypt = scanner.nextLine();

        if(encrypt_decrypt.toLowerCase().equals("e"))
            bind(Integer.class).toInstance(0);
        else if(encrypt_decrypt.toLowerCase().equals("d"))
            bind(Integer.class).toInstance(1);

        bind(File.class).toInstance(file);

        Injector injector = Guice.createInjector(new AlgorithmModule());
        EncryptionDecorator algorithm = injector.getInstance(EncryptionDecorator.class);

        bind(EncryptionDecorator.class).toInstance(algorithm);

        bind(new TypeLiteral<List<Byte>>(){}).toInstance(key);
    }
}
