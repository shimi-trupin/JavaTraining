package javatraining;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        byte[] data;
        EncryptionResult encryptionResult;
        String path, file_path;

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

                    try {
                        encryptionResult = caesar.encrypt(Files.readAllBytes(file.toPath()), KeyGen.randKey());

                        data = encryptionResult.getData();
                        path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                        new FileCreator().creatFile(path, data);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    /*Caesar caesar = new Caesar();
                    caesar.encrypt(file);*/

                    break;
                case "2":
                    //XOR Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    Xor xor = new Xor(new EncryptionBase());
                    xor.register(observer);
                    try {
                        encryptionResult = xor.encrypt(Files.readAllBytes(file.toPath()), KeyGen.randKey());

                        data = encryptionResult.getData();
                        path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                        new FileCreator().creatFile(path, data);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    /*encryption = new Xor();
                    encryption.encrypt(file);*/

                    break;
                case "3":
                    //Multiplication Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    Multiplication multiplication = new Multiplication(new EncryptionBase());
                    multiplication.register(observer);
//                    multiplication.encrypt(file, KeyGen.randOddKey());

                    try {
                        encryptionResult = multiplication.encrypt(Files.readAllBytes(file.toPath()), KeyGen.randOddKey());

                        data = encryptionResult.getData();
                        path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                        new FileCreator().creatFile(path, data);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*encryption = new Multiplication();
                    encryption.encrypt(file);*/

                    break;
                /*case "4":// TODO: 24/07/2016 add options to menu, add sub selection of algs and rand key (odd if needed)
                    file = fileOpener.openFile(System.in, System.out);
                    Xor xor1 = new Xor(new EncryptionBase());
                    Caesar caesar1 = new Caesar(new EncryptionBase());
                    Double<Encryption> caesarXor = new Double<Encryption>(caesar1, xor1);
                    caesarXor.encrypt(file, KeyGen.randKey(), KeyGen.randKey());
                    break;
                case "5":
                    Caesar caesar2 = new Caesar(new EncryptionBase());// TODO: 24/07/2016 write general Caesar caesar, Xor xor before switch case
                    Reverse<Encryption> encryptionReverse = new Reverse<>(caesar2);
                    encryptionReverse.encrypt(new FileOpener().openFile(System.in, System.out), KeyGen.randKey());
                    break;*/
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

                    try {
                        data = caesar.decrypt(Files.readAllBytes(file.toPath()), key);

                        path = file.getAbsolutePath(); // TODO: 25/07/2016 finish this part
                        path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                        file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                        file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                        new FileCreator().creatFile(file_path, data);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
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
                    try {
                        data = xor.decrypt(Files.readAllBytes(file.toPath()), key);

                        path = file.getAbsolutePath();
                        path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                        file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                        file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                        new FileCreator().creatFile(file_path, data);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    /*encryption = new Xor();
                    encryption.decrypt(file, key);*/

                    break;
                case "3":
                    //Multiplication Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    System.out.println("Enter key");
                    try {
                        key = scanner.nextByte();
                        if(key % 2 == 0)
                        {
                            throw new IllegalKeyException("Illegal key! Key cannot be divided by 2");
                        }
                        Multiplication multiplication = new Multiplication(new EncryptionBase());
                        multiplication.register(observer);
                        try {
                            data = multiplication.decrypt(Files.readAllBytes(file.toPath()), key);

                            path = file.getAbsolutePath();
                            path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                            file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                            file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                            new FileCreator().creatFile(file_path, data);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        /*encryption = new Multiplication();
                        encryption.decrypt(file, key);*/

                    } catch (IllegalKeyException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                /*case "4":
                    file = fileOpener.openFile(System.in, System.out);
                    System.out.println("You also need to enter the 'key.bin' file location.");
                    File keysFile = fileOpener.openFile(System.in, System.out);

                    try {
                        byte[] keys = Files.readAllBytes(keysFile.toPath());//file to bytes

                        Xor xor1 = new Xor(new EncryptionBase());
                        Caesar caesar1 = new Caesar(new EncryptionBase());
                        Double<Encryption> caesarXor = new Double<Encryption>(caesar1, xor1);

                        caesarXor.decrypt(file, keys);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;*/
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
