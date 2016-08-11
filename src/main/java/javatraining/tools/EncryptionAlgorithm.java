package javatraining.tools;


import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by shimi on 08/08/2016.
 */
@XmlRootElement
public class EncryptionAlgorithm {

     private String AlgorithmName;

      @XmlElement
      public void setAlgorithmName(String algorithmName) {
            AlgorithmName = algorithmName;
      }

      public String getAlgorithmName() {

            return AlgorithmName;
      }
}
