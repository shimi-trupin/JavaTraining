package javatraining;

import javatraining.designPatterns.Encryption;
import javatraining.tools.*;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shimi on 08/08/2016.
 */
public class Driver {

    private static File file;
    private static FileOpener fileOpener = new FileOpener();

    public static void main(String[] args) {

        System.out.println("Choose an action (enter the number):" + "\n" +
                "1. Encrypt/Decrypt a single file" + "\n" +
                "2. Encrypt/Decrypt a directory" + "\n" +
                "3. Additional algorithms" + "\n" +
                "4. Change default algorithm");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        /*----Declaring Variables----*/
        String encrypt_decrypt;
        Encryption encryption;
        List<Byte> key;
        EncryptionResult encryptionResult;
        byte[] data;
        String path;
        FileCreator fileCreator;
        /*---------------------------*/

        switch (action){
            case 1:
                // Encrypt/Decrypt a single file
                System.out.println("Type 'E' for encryption, or type 'D' for decryption");
                scanner = new Scanner(System.in);
                encrypt_decrypt = scanner.nextLine();


                if(encrypt_decrypt.toLowerCase().equals("e"))
                {
                    //open file
                    file = fileOpener.openFile(System.in, System.out);

                    //gets default algorithm
                    AlgorithmFactory algorithmFactory = new AlgorithmFactory();
                    encryption = algorithmFactory.getDefault();

                    //create random key
                    key = new ArrayList<>();
                    key.add(KeyGen.randKey());

                    //encryption
                    encryptionResult = encryption.encrypt(fileToByteArray(file), key);

                    //create encrypted file and "key.bin" file
                    data = encryptionResult.getData();
                    path = file.getAbsolutePath() + ".encrypted";//add .encrypted to end of file name

                    fileCreator = new FileCreator();
                    fileCreator.createFile(path, data);

                    path = path.substring(0, path.lastIndexOf("\\")) + "\\key.bin";
                    fileCreator.serializeKey(path, key);

                }
                else if (encrypt_decrypt.toLowerCase().equals("d"))
                {

                }
                else System.out.println("Wrong input!");
                break;
            case 2:
                // Encrypt/Decrypt a directory
                break;
            case 3:
                // Additional algorithms
                break;
            case 4:
                // Change default algorithm
                break;
            default:
                System.out.println("Wrong input!");
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
