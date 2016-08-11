package javatraining;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.designPatterns.StartEndObserver;
import javatraining.dirEncryption.AsyncTask;
import javatraining.dirEncryption.SyncDir;
import javatraining.modules.AlgorithmModule;
import javatraining.modules.ChangeDefaultModule;
import javatraining.modules.SyncDirModule;
import javatraining.tools.*;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by shimi on 08/08/2016.
 */
public class Driver {

    private static File file;
    private static FileOpener fileOpener = new FileOpener();

    public static void main(String[] args) {
        checkForConfigXml();
        System.out.println("Choose an action (enter the number):" + "\n" +
                "1. Encrypt/Decrypt a single file" + "\n" +
                "2. Encrypt/Decrypt a directory" + "\n" +
                "3. Change default algorithm" + "\n" +
                "4. ");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        /*----Declaring Variables----*/
        String encrypt_decrypt;
        EncryptionDecorator encryption;
        List<Byte> key;
        EncryptionResult encryptionResult;
        byte[] data;
        String path, file_path, type;
        FileCreator fileCreator;
        Injector injector;
        StartEndObserver observer = new StartEndObserver();
        long time;
        /*---------------------------*/

        switch (action){
            case 1:
                // Encrypt/Decrypt a single file
                System.out.println("Type 'E' for encryption, or type 'D' for decryption");
                scanner = new Scanner(System.in);// TODO: 10/08/2016 injection?
                encrypt_decrypt = scanner.nextLine();


                /*----------------ENCRYPTION SINGLE FILE----------------*/
                if(encrypt_decrypt.toLowerCase().equals("e"))
                {
                    //gets default algorithm
/*                    AlgorithmFactory algorithmFactory = new AlgorithmFactory();
                    encryption = algorithmFactory.getDefault();*/
                    injector = Guice.createInjector(new AlgorithmModule());
                    encryption = injector.getInstance(EncryptionDecorator.class);

                    //open file
                    file = fileOpener.openFile(System.in, System.out);

                    //create random key
                    key = new ArrayList<>();
                    type = encryption.getClass().getSimpleName().toLowerCase();
                    if (type.contains("double") || type.contains("split")) {
                        if (type.contains("multiplication"))
                        {
                            key.add(KeyGen.randOddKey());
                            key.add(KeyGen.randOddKey());
                        }
                        else {
                            key.add(KeyGen.randKey());
                            key.add(KeyGen.randKey());
                        }
                    }
                    else if (type.contains("multiplication"))
                        key.add(KeyGen.randOddKey());
                    else key.add(KeyGen.randKey());


                        //encryption
                    try {
                        encryptionResult = encryption.encrypt(fileToByteArray(file), key);
                    } catch (Exception e) {
                        //// TODO:
                        break;
                    }

                    //create encrypted file and "key.bin" file
                    data = encryptionResult.getData();
                    path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                    fileCreator = new FileCreator();
                    fileCreator.createFile(path, data);

                    path = path.substring(0, path.lastIndexOf("\\")) + "\\key.bin";
                    fileCreator.serializeKey(path, key);

                }

                /*----------------DECRYPTION SINGLE FILE----------------*/
                else if (encrypt_decrypt.toLowerCase().equals("d"))
                {
                    injector = Guice.createInjector(new AlgorithmModule());
                    encryption = injector.getInstance(EncryptionDecorator.class);

                    //open file
                    file = fileOpener.openFile(System.in, System.out);

                    //get key
                    System.out.println("Enter source to 'key.bin' file:");
                    path = scanner.nextLine();
                    key = FileOpener.getKeysDeserialization(path);

                    encryption.register(observer);

                    //decryption
                    try {
                        data = encryption.decrypt(fileToByteArray(file), key);
                    } catch (Exception e) {
                        e.printStackTrace();//TODO
                        break;
                    }

                    //create decrypted file
                    path = file.getAbsolutePath();
                    if (path.lastIndexOf(".encrypted")!= -1)
                        path = path.substring(0, path.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
                    file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
                    file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

                    fileCreator = new FileCreator();
                    fileCreator.createFile(file_path, data);

                    path = path.substring(0, path.lastIndexOf("\\")) + "\\key.bin";
                    fileCreator.serializeKey(path, key);
                }
                else System.out.println("Wrong input!");
                break;
            case 2:
                // Encrypt/Decrypt a directory
                System.out.println("choose the wanted number:\n" +
                        "1. Sync \n" +
                        "2. Async\n");
                scanner = new Scanner(System.in);// TODO: 10/08/2016 injection?
                action = scanner.nextInt();
                if (action == 1)
                {
                    //sync
                    injector = Guice.createInjector(new SyncDirModule());
                    SyncDir syncDir = injector.getInstance(SyncDir.class);

                    Thread thread = new Thread(syncDir);
                    time = System.currentTimeMillis();
                    thread.start();
                    try {
                        thread.join();
                        System.out.println("\nEncryption took " + Long.toString(System.currentTimeMillis() - time) + " milliseconds");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if (action == 2) {
                    //async
/*                    injector = Guice.createInjector(new AlgorithmModule());
                    encryption = injector.getInstance(EncryptionDecorator.class);*/


                    //open dir
                    System.out.println("Enter a path to source directory:");
                    scanner = new Scanner(System.in);
                    path = scanner.nextLine();

                    File dir = new File(path);
                    if (!dir.isDirectory())
                        return;
                    //load files from dir
                    File[] fileList = dir.listFiles();
                    if (fileList.length == 0)
                        return;

                    //open subDir
                    String subDir = path + "\\encrypted_decrypted";
                    if (!(new File(subDir)).mkdir())
                        return;

                    System.out.println("Type 'E' for encryption, or type 'D' for decryption");
                    scanner = new Scanner(System.in);
                    encrypt_decrypt = scanner.nextLine();
                    if (encrypt_decrypt.toLowerCase().equals("e")) {
                        action = 0;
                        //serialize "key.bin" in subDir
                        fileCreator = new FileCreator();

                        injector = Guice.createInjector(new AlgorithmModule());
                        EncryptionDecorator algorithm = injector.getInstance(EncryptionDecorator.class);

                        key = new ArrayList<>();
                        type = algorithm.getClass().getSimpleName().toLowerCase();
                        if (type.contains("double") || type.contains("split")) {
                            if (type.contains("multiplication")) {
                                key.add(KeyGen.randOddKey());
                                key.add(KeyGen.randOddKey());
                            } else {
                                key.add(KeyGen.randKey());
                                key.add(KeyGen.randKey());
                            }
                        } else if (type.contains("multiplication"))
                            key.add(KeyGen.randOddKey());
                        else key.add(KeyGen.randKey());

                        fileCreator.serializeKey(subDir + "\\key.bin", key);
                    } else if (encrypt_decrypt.toLowerCase().equals("d")) {
                        action = 1;
                        System.out.println("Enter source to 'key.bin' file:");
                        path = scanner.nextLine();
                        key = FileOpener.getKeysDeserialization(path);
                    } else return;//todo add else throw new


                    //executor
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    time = System.currentTimeMillis();
                    for (File f : fileList) {
                        injector = Guice.createInjector(new AlgorithmModule());
                        EncryptionDecorator algorithm = injector.getInstance(EncryptionDecorator.class);
                        executorService.execute(new AsyncTask(action, f, key, algorithm));
                    }
                    executorService.shutdown();

                    //print finish time
                    try {
                        boolean timeout = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        System.out.println("\nEncryption took " + Long.toString(System.currentTimeMillis() - time) + " milliseconds");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else System.out.println("Wrong input!");
                break;
            case 3:
                // Change default algorithm
                injector = Guice.createInjector(new ChangeDefaultModule());
                ChangeDefault changeDefault= injector.getInstance(ChangeDefault.class);
                changeDefault.change();
                break;
            case 4:
                //
                break;
            default:
                System.out.println("Wrong input!");
        }
    }

    public static void saveXmlReport (boolean status , String time , Exception e , String pathToFolder ){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlReport.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            XmlReport report = new XmlReport();
            report.status=status? "success" : "failed";
            report.time=time;
            report.excMessage= e.getMessage();
            report.excName = e.getClass().getName();
            report.excStacktrace = e.getStackTrace().toString();

            jaxbMarshaller.marshal(report,new FileWriter(pathToFolder+"report.xml"));

        } catch (Exception ex) {
            e.printStackTrace();
        }

    }




    private static void checkForConfigXml() {

        ClassLoader classLoader = new Driver().getClass().getClassLoader();
        try
        {
        FileInputStream xml = new FileInputStream ( new File ("EncryptionAlgorithm.xml"));
            InputStream xsd = new Driver().getClass().getResourceAsStream( "/EncryptionAlgorithm.xsd" );


            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
        }
        catch(Exception ex)
        {

            try {
                EncryptionAlgorithm defaultAlgorithm = new EncryptionAlgorithm();
                defaultAlgorithm.setAlgorithmName("caesar");

                JAXBContext jaxbContext = JAXBContext.newInstance(EncryptionAlgorithm.class);

                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(defaultAlgorithm,new FileWriter("EncryptionAlgorithm.xml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean validateAgainstXSD(InputStream xml, InputStream xsd)
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
    }

    public static byte[] fileToByteArray(File file)
    {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
