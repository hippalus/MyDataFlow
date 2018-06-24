package pojo;

import com.sun.org.apache.xml.internal.serializer.Serializer;

import java.io.Serializable;


public class StringObject implements Serializable {

    public static final long serialVersionUID=1L;
    private String id;
    private String sentence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
