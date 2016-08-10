package javatraining.tools;

import javatraining.algorithms.Caesar;
import javatraining.designPatterns.EncryptionDecorator;
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
import java.util.List;

/**
 * Created by shimi on 08/08/2016.
 */
public class AlgorithmFactory {
    @Getter @Setter private EncryptionDecorator encryption;
    @Getter @Setter private List<Byte> key;
    @Getter @Setter private File xmlFile;
    @Getter @Setter private File xsdFile;

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

    public EncryptionDecorator getDefault()
    {
        encryption = null;
        if(validate()){
            /////////////////
            try {

//                File file = new File("C:\\file.xml");
                JAXBContext jaxbContext = JAXBContext.newInstance(EncryptionAlgorithm.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                EncryptionAlgorithm encryptionAlgorithm = (EncryptionAlgorithm) jaxbUnmarshaller.unmarshal(xmlFile);
//                System.out.println(encryptionAlgorithm);

                switch (encryptionAlgorithm.getAlgorithmName().toLowerCase()){
                    case "caesar":
                        encryption = new Caesar();
                        break;
                }

            } catch (JAXBException e) {
                e.printStackTrace();
            }
            /////////////////
        }
        else System.out.println("XML file is not valid!");
        return encryption;
    }
}
