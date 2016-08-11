package javatraining.tools;

import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlReport {

    @XmlElement
    public String status;

    @XmlElement
    public String time;

    @XmlElement
    public String excName;
    @XmlElement
    public String excMessage;
    @XmlElement
    public String excStacktrace;
}
