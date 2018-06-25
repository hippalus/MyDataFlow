package producer;

import constants.IKafkaConstants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


public class ProducurCreator {

    public static Producer<String,?> creatProducer(){
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,IKafkaConstants.KAFKA_BROKERS);
        //Producer kimliği, Broker client kaynağını belirleyebilir.
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,IKafkaConstants.CLIENT_ID);
        //Anahtar nesneyi serileştirmek için kullanılacak sınıf. Örneğimizde,
        // bizim anahtarımız  Long, bu yüzden
        // LongSerializer anahtarı serileştirmek için sınıfı kullanabiliriz  .
        // Kullanım durumunuzda anahtar olarak başka bir nesne kullanıyorsanız  ,
        // Kafka'nın Serializer arayüzünü uygulayarak ve serialize yöntemi geçersiz kılarak
        // özel seri hale getirici sınıfınızı oluşturabilirsiniz  .
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        //Değer nesnesini serileştirmek için kullanılacak sınıf. Bizim örneğimizde,
        // bizim değeriniz  String, bu yüzden StringSerializer anahtarı
        // serileştirmek için sınıfı kullanabiliriz  .
        // Değeriniz başka bir nesne ise, o zaman özel serializer sınıfınızı yaratırsınız.
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        return  new KafkaProducer<>(properties);
    }
}
