package javatraining.modules;

import com.google.inject.*;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.FileOpener;
import javatraining.tools.KeyGen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shimi on 10/08/2016.
 */
public class SyncDirModule extends AbstractModule {
    @Override
    protected void configure() {

        System.out.println("Type 'E' for encryption, or type 'D' for decryption");
        Scanner scanner = new Scanner(System.in);// TODO: 10/08/2016 injection?
        String encrypt_decrypt = scanner.nextLine();

        if(encrypt_decrypt.toLowerCase().equals("e"))
            bind(Integer.class).toInstance(0);
        else if(encrypt_decrypt.toLowerCase().equals("d"))
            bind(Integer.class).toInstance(1);

        //open file
        /*File dir = new FileOpener().openFile(System.in, System.out);
        bind(String.class).toInstance(dir.getAbsolutePath());*/
        System.out.println("Enter a path to source directory:");
        scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        bind(String.class).toInstance(path);

        Injector injector = Guice.createInjector(new AlgorithmModule());
        EncryptionDecorator algorithm = injector.getInstance(EncryptionDecorator.class);

        bind(EncryptionDecorator.class).toInstance(algorithm);

        List<Byte> key = new ArrayList<>();
        String type = algorithm.getClass().getSimpleName();
        if (type.toLowerCase().contains("double") || type.toLowerCase().contains("split")) {
            if (type.toLowerCase().contains("multiplication"))
            {
                key.add(KeyGen.randOddKey());
                key.add(KeyGen.randOddKey());
            }
            else {
                key.add(KeyGen.randKey());
                key.add(KeyGen.randKey());
            }
        }
        else if (type.toLowerCase().contains("multiplication"))
            key.add(KeyGen.randOddKey());
        else key.add(KeyGen.randKey());

        bind(new TypeLiteral<List<Byte>>(){}).toInstance(key);
    }
}
