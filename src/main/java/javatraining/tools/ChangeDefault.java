package javatraining.tools;

import com.google.inject.Inject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;

/**
 * Created by shimi on 10/08/2016.
 */
public class ChangeDefault {

//    private String name;
    EncryptionAlgorithm algorithm;
    File xml;

    @Inject
    public ChangeDefault(EncryptionAlgorithm algorithm, File xml) {
        this.algorithm = algorithm;
        this.xml = xml;
    }

    public void change(){
        try {

//            File file = new File("C:\\file.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(EncryptionAlgorithm.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            System.out.println(xml.getAbsolutePath());
            jaxbMarshaller.marshal(algorithm, xml);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
