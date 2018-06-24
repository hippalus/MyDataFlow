package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import pojo.StringObject;

import java.util.Map;

public class CustomDeserializer implements Deserializer<StringObject> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public StringObject deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        StringObject object = null;
        try {
            object = mapper.readValue(data, StringObjectgituch.class);
        } catch (Exception exception) {
            System.out.println("Error in deserializing bytes " + exception);
        }
        return object;
    }

    @Override
    public void close() {

    }
}
