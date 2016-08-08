package javatraining;

import javatraining.tools.FileOpener;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.InputStream;
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
                "3. Additional javatraining.algorithms" + "\n" +
                "4. Change default algorithm");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        String encrypt_decrypt;

        switch (action){
            case 1:
                // Encrypt/Decrypt a single file
                System.out.println("Type 'E' for encryption, or type 'D' for decryption");
                encrypt_decrypt = scanner.nextLine();


                if(encrypt_decrypt.toLowerCase().equals("e"))
                {
                    file = fileOpener.openFile(System.in, System.out);

//                    ClassLoader classLoader = getClass().getClassLoader();
//                    File file = new File(classLoader.getResource("EncryptionAlgorithm.xml").getFile());

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
                // Additional javatraining.algorithms
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
}
