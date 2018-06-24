package serializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import pojo.StringObject;

import java.util.Map;


public class CustomSerialize implements Serializer<StringObject> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, StringObject data ) {
        byte[] val=null;
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            val=objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void close() {

    }
}
