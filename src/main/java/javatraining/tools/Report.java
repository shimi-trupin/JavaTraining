package javatraining.tools;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by shimi on 08/08/2016.
 */
@XmlRootElement
public class Report {
    @Getter private boolean success;
    @Getter private long time;
    @Getter private String exceptionName;
    @Getter private String exceptionMessage;
    @Getter private String exceptionStackTrace;

    @XmlElement
    public void setSuccess(boolean success) {
        this.success = success;
    }
    @XmlElement
    public void setTime(long time) {
        this.time = time;
    }
    @XmlElement
    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
    @XmlElement
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    @XmlElement
    public void setExceptionStackTrace(String exceptionStackTrace) {
        this.exceptionStackTrace = exceptionStackTrace;
    }
}
