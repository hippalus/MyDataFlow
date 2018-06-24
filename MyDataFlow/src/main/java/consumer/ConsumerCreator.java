package consumer;


import constants.IKafkaConstants;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;

import java.beans.Customizer;
import java.util.Collections;
import java.util.Properties;

public class ConsumerCreator {

    public static Consumer<Long,String> createConsumer(){

        final Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,IKafkaConstants.KAFKA_BROKERS);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,IKafkaConstants.CLIENT_ID);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,LongDeserializer.class.getName());
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,IKafkaConstants.MAX_POLL_RECORDS);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,IKafkaConstants.OFFSET_RESET_EARLIER);
        final Consumer<Long,String> consumer=new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIK_NAME));
        return consumer;
    }
}
