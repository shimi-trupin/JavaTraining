package javatraining;

import java.io.File;
import java.util.Scanner;

/**
 * Created by shimi on 30/05/2016.
 * Driver of the program
 */
public class App {
    public static void main(String[] args)
    {
        System.out.println("Type 'e' for encryption, or type 'd' for decryption");
        Scanner scanner = new Scanner(System.in);
        File file;
        Byte key;
        Encryption encryption;
        FileOpener fileOpener = new FileOpener();
        StartEndObserver observer = new StartEndObserver();

        String inp = scanner.nextLine();//scan for user input
        if (inp.equals("e") || inp.equals("E"))//encryption
        {
            System.out.println("Choose encryption algorithm (enter a number):\n1. Caesar\n2. XOR\n3. Multiplication");
            inp = scanner.nextLine();
            switch (inp) {
                case "1":
                    //Caesar Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    Caesar caesar = new Caesar(new EncryptionBase());

                    caesar.register(observer);

                    caesar.encrypt(file);

                    /*Caesar caesar = new Caesar();
                    caesar.encrypt(file);*/

                    break;
                case "2":
                    //XOR Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    Xor xor = new Xor(new EncryptionBase());
                    xor.register(observer);
                    xor.encrypt(file);

                    /*encryption = new Xor();
                    encryption.encrypt(file);*/

                    break;
                case "3":
                    //Multiplication Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    Multiplication multiplication = new Multiplication(new EncryptionBase());
                    multiplication.register(observer);
                    multiplication.encrypt(file);

                    /*encryption = new Multiplication();
                    encryption.encrypt(file);*/

                    break;
                default:
                    System.out.println("Wrong Input! make sure you enter the right number.");
                    break;
            }
//            EncryptionClass.Encrypt();
        }
        else if (inp.toLowerCase().equals("d"))//decryption
        {
            System.out.println("Choose decryption algorithm (enter a number):\n" +
                    "1. Caesar\n" +
                    "2. XOR\n" +
                    "3. Multiplication");
            inp = scanner.nextLine();
            switch (inp) {
                case "1":
                    //Caesar Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    System.out.println("Enter key");
                    key = scanner.nextByte();
                    Caesar caesar = new Caesar(new EncryptionBase());

                    caesar.register(observer);

                    caesar.decrypt(file, key);
//                    Caesar caesar = new Caesar();
//                    caesar.decrypt(file, key);

                    break;
                case "2":
                    //XOR Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    System.out.println("Enter key");
                    key = scanner.nextByte();
                    Xor xor = new Xor(new EncryptionBase());
                    xor.register(observer);
                    xor.decrypt(file, key);

                    /*encryption = new Xor();
                    encryption.decrypt(file, key);*/

                    break;
                case "3":
                    //Multiplication Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    System.out.println("Enter key");
                    try {
                        key = scanner.nextByte();// TODO: 10/07/2016 throw exception if key is illegal
                        if(key % 2 == 0)
                        {
                            throw new IllegalKeyException("Illegal key! Key cannot be divided by 2");
                        }
                        Multiplication multiplication = new Multiplication(new EncryptionBase());
                        multiplication.register(observer);
                        multiplication.decrypt(file, key);

                        /*encryption = new Multiplication();
                        encryption.decrypt(file, key);*/

                    } catch (IllegalKeyException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    System.out.println("Wrong Input! make sure you enter the right number.");
                    break;
            }
//            Decryption.decrypt();
        }
        else
            System.out.println("Incorrect Input");
//        File file = new File(src);

    }

    /*
    * Helper function
    * scans a file path from user, verifies it, and if all is good - returns file.
    * Otherwise, keeps scanning for path.
    * */
    private static File openFile()
    {
        File file;
        Scanner scanner = new Scanner(System.in);
        String path;
        while (true)
        {
            System.out.println("Enter a path to source file");
            path = scanner.nextLine();//scan for path from user
            try {
                file = new File(path);
                if (file.exists() && file.isFile()) {
                    return file;
                }
                else System.out.println("Incorrect path. Try again!");
            }
            catch (Exception e)
            {
                // if any error occurs
                e.printStackTrace();
            }

        }
    }
}
