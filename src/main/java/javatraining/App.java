package javatraining;

import javatraining.algorithms.Caesar;
import javatraining.algorithms.Multiplication;
import javatraining.algorithms.Xor;
import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionBase;
import javatraining.designPatterns.StartEndObserver;
import javatraining.dirEncryption.AsyncTask;
import javatraining.dirEncryption.SyncDir;
import javatraining.exceptions.IllegalKeyException;
import javatraining.specialAlgorithms.Split;
import javatraining.tools.EncryptionResult;
import javatraining.tools.FileCreator;
import javatraining.tools.FileOpener;
import javatraining.tools.KeyGen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by shimi on 30/05/2016.
 * Driver of the program
 */
public class App {
    private static final int ENCRYPTION = 0;
    private static final int DECRYPTION = 1;

    public static void main(String[] args)
    {/*
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
        List<Byte> keys;
        FileCreator fileCreator;
        Caesar caesar;
        Xor xor;
        Multiplication multiplication;
        long time;


        String inp = scanner.nextLine();//scan for user input
        if (inp.equals("e") || inp.equals("E"))//encryption
        {
            System.out.println("Choose encryption algorithm (enter a number):\n1. Caesar\n2. XOR\n3. Multiplication");
            inp = scanner.nextLine();
            switch (inp) {
                case "1":
                    //Caesar Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    caesar = new Caesar(new EncryptionBase());
                    keys = new ArrayList<>();
                    keys.add(KeyGen.randKey());

                    caesar.register(observer);

                    try {
                        encryptionResult = caesar.encrypt(Files.readAllBytes(file.toPath()), keys);

                        data = encryptionResult.getData();
                        path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                        fileCreator = new FileCreator();
                        fileCreator.createFile(path, data);

                        path = path.substring(0, path.lastIndexOf("\\")) + "\\key.bin";
                        fileCreator.serializeKey(path, keys);
            }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    *//*Caesar caesar = new Caesar();
                    caesar.encrypt(file);*//*

                    break;
                case "2":
                    //XOR Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    xor = new Xor(new EncryptionBase());
                    keys = new ArrayList<>();
                    keys.add(KeyGen.randKey());
                    xor.register(observer);
                    try {
                        encryptionResult = xor.encrypt(Files.readAllBytes(file.toPath()), keys);

                        data = encryptionResult.getData();
                        path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                        fileCreator = new FileCreator();
                        fileCreator.createFile(path, data);

                        path = path.substring(0, path.lastIndexOf("\\")) + "\\key.bin";
                        fileCreator.serializeKey(path, keys);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    *//*encryption = new Xor();
                    encryption.encrypt(file);*//*

                    break;
                case "3":
                    //Multiplication Algorithm
                    file = fileOpener.openFile(System.in, System.out);
                    multiplication = new Multiplication(new EncryptionBase());
                    keys = new ArrayList<>();
                    keys.add(KeyGen.randOddKey());
                    multiplication.register(observer);
//                    multiplication.encrypt(file, KeyGen.randOddKey());

                    try {
                        encryptionResult = multiplication.encrypt(Files.readAllBytes(file.toPath()), keys);

                        data = encryptionResult.getData();
                        path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                        fileCreator = new FileCreator();
                        fileCreator.createFile(path, data);

                        path = path.substring(0, path.lastIndexOf("\\")) + "\\key.bin";
                        fileCreator.serializeKey(path, keys);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    *//*encryption = new Multiplication();
                    encryption.encrypt(file);*//*

                    break;
                *//*case "4":// TODO: 24/07/2016 add options to menu, add sub selection of algs and rand key (odd if needed)
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
                    break;*//*
                case "6":
                    path = "C:\\Users\\shimi\\Desktop\\Untitled-5.jpg";
                    caesar = new Caesar(new EncryptionBase());
                    Split<Encryption> split = new Split<>(caesar);
                    keys = new ArrayList<>();
                    keys.add((byte)1);
                    keys.add((byte)2);
//                    split.encrypt(new File(path), (byte)1, (byte)2);
                    try {
                        encryptionResult = split.encrypt(Files.readAllBytes(Paths.get(path)), keys);

                        data = encryptionResult.getData();
                        path = *//*file.getAbsolutePath()*//* path + ".encrypted";//add .encrypted to end of file name

                        fileCreator = new FileCreator();
                        fileCreator.createFile(path, data);

                        path = "C:\\Users\\shimi\\Desktop\\key.bin";
                        fileCreator.serializeKey(path, keys);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "8":
                    caesar = new Caesar(new EncryptionBase());
                    keys = new ArrayList<>();
                    keys.add(KeyGen.randKey());
                    SyncDir<Encryption> syncDir = new SyncDir<>(ENCRYPTION, caesar, "C:\\Users\\shimi\\Desktop\\03.04.2015", keys);
                    Thread t = *//*(*//*new Thread(syncDir)*//*).start()*//*;
                    time = System.currentTimeMillis();
                    t.start();
                    try {
                        t.join();
                        System.out.println("\nEncryption took " + Long.toString(System.currentTimeMillis() - time) + " milliseconds");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "9":
                    //open dir
                    String dirPath = "C:\\Users\\shimi\\Desktop\\03.04.2015";
                    File dir = new File(dirPath);
                    if (!dir.isDirectory())
                        return;
                    //load files from dir
                    File[] fileList = dir.listFiles();
                    if (fileList.length == 0)
                        return;

                    //open subDir
                    String subDir = dirPath + "\\encrypted_decrypted";
                    if(!(new File(subDir)).mkdir())
                        return;
                    //serialize "key.bin" in subDir
                    fileCreator = new FileCreator();
                    keys = new ArrayList<>();
                    keys.add((byte)1);
                    fileCreator.serializeKey(subDir + "\\key.bin", keys);

                    //executor
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    time = System.currentTimeMillis();
                    for (File f: fileList){
                        executorService.execute(new AsyncTask<Encryption>(ENCRYPTION, f, keys, new Caesar(new EncryptionBase())));
                    }
                    executorService.shutdown();

                    //print finish time
                    try {
                        boolean timeout = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        System.out.println("\nEncryption took " + Long.toString(System.currentTimeMillis() - time) + " milliseconds");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Wrong Input! make sure you enter the right number.");
                    break;
            }
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
                    System.out.println("Enter source to 'key.bin' file:");
                    path = scanner.nextLine();
                    caesar = new Caesar(new EncryptionBase());
                    keys = FileOpener.getKeysDeserialization(path);
//                    keys.add(key);

                    caesar.register(observer);

                    try {
                        data = caesar.decrypt(Files.readAllBytes(file.toPath()), keys);

                        path = file.getAbsolutePath();
                        path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                        file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                        file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                        new FileCreator().createFile(file_path, data);
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
//                    System.out.println("Enter key");
//                    key = scanner.nextByte();
                    System.out.println("Enter source to 'key.bin' file:");
                    path = scanner.nextLine();
                    xor = new Xor(new EncryptionBase());
                    *//*keys = new ArrayList<>();
                    keys.add(key);*//*
                    keys = FileOpener.getKeysDeserialization(path);
                    xor.register(observer);
                    try {
                        data = xor.decrypt(Files.readAllBytes(file.toPath()), keys);

                        path = file.getAbsolutePath();
                        path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                        file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                        file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                        new FileCreator().createFile(file_path, data);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    *//*encryption = new Xor();
                    encryption.decrypt(file, key);*//*

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
                        multiplication = new Multiplication(new EncryptionBase());
                        keys = new ArrayList<>();
                        keys.add(key);
                        multiplication.register(observer);
                        try {
                            data = multiplication.decrypt(Files.readAllBytes(file.toPath()), keys);

                            path = file.getAbsolutePath();
                            path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                            file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                            file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                            new FileCreator().createFile(file_path, data);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        *//*encryption = new Multiplication();
                        encryption.decrypt(file, key);*//*

                    } catch (IllegalKeyException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                *//*case "4":
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
                    break;*//*
                case "6":
                    try {
                        byte[] data1 = Files.readAllBytes(Paths.get("C:\\Users\\shimi\\Desktop\\Untitled-5.jpg.encrypted"));
                        *//*System.out.println("before:");
                        for (byte b: data1)
                            System.out.println(b);*//*
                        keys = FileOpener.getKeysDeserialization("C:\\Users\\shimi\\Desktop\\key.bin");
                        System.out.println(keys.get(0) + " , " + keys.get(1));
                        Caesar caesar1 = new Caesar(new EncryptionBase());
                        Split<Encryption> split = new Split<>(caesar1);
                        data1 = split.decrypt(data1, keys);
                        new FileCreator().createFile("C:\\Users\\shimi\\Desktop\\Untitled-5_decrypted.jpg", data1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "8":
                    caesar = new Caesar(new EncryptionBase());
                    keys = new ArrayList<>();
                    keys.add(KeyGen.randKey());
                    SyncDir<Encryption> syncDir = new SyncDir<>(DECRYPTION, caesar, "C:\\Users\\shimi\\Desktop\\03.04.2015\\encrypted_decrypted", keys);
                    (new Thread(syncDir)).start();
                    break;
                default:
                    System.out.println("Wrong Input! make sure you enter the right number.");
                    break;
            }
        }
        else
            System.out.println("Incorrect Input");*/

    }

    /*public static boolean validateAgainstXSD(InputStream xml, InputStream xsd)
    {
        try
        {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }*/
}
