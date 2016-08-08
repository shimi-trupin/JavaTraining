package javatraining.tools;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by shimi on 08/08/2016.
 */
@XmlRootElement
public class EncryptionAlgorithm {
    String AlgorithmName;


    public String getAlgorithmName() {
        return AlgorithmName;
    }

    @XmlElement
    public void setAlgorithmName(String algorithmName) {
        this.AlgorithmName = algorithmName;
    }
}
