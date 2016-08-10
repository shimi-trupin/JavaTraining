package javatraining.modules;

import com.google.inject.AbstractModule;
import javatraining.tools.EncryptionAlgorithm;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

/**
 * Created by shimi on 10/08/2016.
 */
public class ChangeDefaultModule extends AbstractModule {
    @Override
    protected void configure() {
        EncryptionAlgorithm algorithm = new EncryptionAlgorithm();
        algorithm.setAlgorithmName(algorithmSelector());
        bind(EncryptionAlgorithm.class).toInstance(algorithm);

        ClassLoader classLoader = getClass().getClassLoader();
        File xmlFile = new File(classLoader.getResource("EncryptionAlgorithm.xml").getFile());
        String xmlPath = xmlFile.getAbsolutePath();
        try {
            xmlPath = URLDecoder.decode(xmlPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bind(File.class).toInstance(new File(xmlPath));
    }

    private String algorithmSelector()
    {
        String name = "";
        System.out.println("Choose an algorithm: (select a number)\n" +
                "1. Caesar\n" +
                "2. XOR\n" +
                "3. Multiplication\n" +
                "4. Double\n" +
                "5. Reverse\n" +
                "6. Split\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                name = "caesar";
                break;
            case 2:
                name = "xor";
                break;
            case 3:
                name = "multiplication";
                break;
            case 4:
                name = "Double";
                System.out.println("Choose the first algorithm: (select a number)\n" +
                        "1. Caesar\n" +
                        "2. XOR\n" +
                        "3. Multiplication\n");
                choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 1:
                        name += "_caesar";
                    case 2:
                        name += "_xor";
                        break;
                    case 3:
                        name += "_multiplication";
                        break;

                    default:
                        return name;
                }
                System.out.println("Choose the second algorithm: (select a number)\n" +
                        "1. Caesar\n" +
                        "2. XOR\n" +
                        "3. Multiplication\n");
                choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 1:
                        name += "_caesar";
                    case 2:
                        name += "_xor";
                        break;
                    case 3:
                        name += "_multiplication";
                        break;

                    default:
                        return name;
                }
                break;
            case 5:
                name = "reverse";
                System.out.println("Choose an algorithm: (select a number)\n" +
                        "1. Caesar\n" +
                        "2. XOR\n" +
                        "3. Multiplication\n");
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        name += "_caesar";
                        break;
                    case 2:
                        name += "_xor";
                        break;
                    case 3:
                        name += "_multiplication";
                        break;
                    default:
                        return name;
                }
                break;
            case 6:
                name = "split";
                System.out.println("Choose an algorithm: (select a number)\n" +
                        "1. Caesar\n" +
                        "2. XOR\n" +
                        "3. Multiplication\n");
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        name += "_caesar";
                        break;
                    case 2:
                        name += "_xor";
                        break;
                    case 3:
                        name += "_multiplication";
                        break;
                    default:
                        return name;
                }
                break;
        }
        return name;
    }
}
