package javatraining.modules;

import com.google.inject.AbstractModule;
import javatraining.algorithms.Caesar;
import javatraining.algorithms.Multiplication;
import javatraining.algorithms.Xor;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.specialAlgorithms.Double;
import javatraining.specialAlgorithms.Reverse;
import javatraining.specialAlgorithms.Split;
import javatraining.tools.EncryptionAlgorithm;
import lombok.Getter;
import lombok.Setter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * Created by shimi on 10/08/2016.
 */
public class AlgorithmModule extends AbstractModule {

    @Getter @Setter private File xmlFile;
    @Getter @Setter private File xsdFile;


    @Override
    protected void configure() {
        if(validate()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(EncryptionAlgorithm.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                EncryptionAlgorithm encryptionAlgorithm = (EncryptionAlgorithm) jaxbUnmarshaller.unmarshal(xmlFile);

                switch (encryptionAlgorithm.getAlgorithmName().toLowerCase()) {
                    case "caesar":
//                        algorithm = new Caesar();
                        bind(EncryptionDecorator.class).toInstance( new Caesar());
                        break;
                    case "xor":
//                        algorithm = new Xor();
                        bind(EncryptionDecorator.class).toInstance(new Xor());
                        break;
                    case "multiplication":
//                        algorithm = new Multiplication();
                        bind(EncryptionDecorator.class).toInstance( new Multiplication());
                        break;


                    case "double_caesar_caesar":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Caesar(), new Caesar()));
                        break;
                    case "double_caesar_xor":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Caesar(), new Xor()));
                        break;
                    case "double_caesar_multiplication":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Caesar(), new Multiplication()));
                        break;
                    case "double_xor_caesar":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Xor(), new Caesar()));
                        break;
                    case "double_xor_xor":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Xor(), new Xor()));
                        break;
                    case "double_xor_multiplication":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Xor(), new Multiplication()));
                        break;
                    case "double_multiplication_caesar":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Multiplication(), new Caesar()));
                        break;
                    case "double_multiplication_xor":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Multiplication(), new Xor()));
                        break;
                    case "double_multiplication_multiplication":
                        bind(EncryptionDecorator.class).toInstance(new Double(new Multiplication(), new Multiplication()));
                        break;


                    case "reverse_caesar":
                        bind(EncryptionDecorator.class).toInstance(new Reverse(new Caesar()));
                        break;
                    case "reverse_xor":
                        bind(EncryptionDecorator.class).toInstance(new Reverse(new Xor()));
                        break;
                    case "reverse_multiplication":
                        bind(EncryptionDecorator.class).toInstance(new Reverse(new Multiplication()));
                        break;


                    case "split_caesar":
                        bind(EncryptionDecorator.class).toInstance(new Split(new Caesar()));
                        break;
                    case "split_xor":
                        bind(EncryptionDecorator.class).toInstance(new Split(new Xor()));
                        break;
                    case "split_multiplication":
                        bind(EncryptionDecorator.class).toInstance(new Split(new Multiplication()));
                        break;
                }

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validate() {
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            setXmlFile(new File(classLoader.getResource("EncryptionAlgorithm.xml").getFile()));
            setXsdFile(new File(classLoader.getResource("EncryptionAlgorithm.xsd").getFile()));
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

        try
        {
            String xmlPath = xmlFile.getAbsolutePath();
            xmlPath = URLDecoder.decode(xmlPath, "UTF-8");
            String xsdPath = xsdFile.getAbsolutePath();
            xsdPath = URLDecoder.decode(xsdPath, "UTF-8");
            InputStream xml = new FileInputStream(xmlPath);
            InputStream xsd = new FileInputStream(xsdPath);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
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
