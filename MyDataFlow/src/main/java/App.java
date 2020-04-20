import constants.IKafkaConstants;
import consumer.ConsumerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import producer.ProducurCreator;

import java.util.concurrent.ExecutionException;

public class App {


    public static void main(String[] args) {
runProducer();
runconsumer();

    }


   protected static void runconsumer(){

        Consumer<String,String> consumer= (Consumer<String, String>) ConsumerCreator.createConsumer();
        int noMessageFound=0;
        while(true){

            final ConsumerRecords<String,String> consumerRecords= (ConsumerRecords<String, String>) consumer.poll(1000000);
            // 1000, milisaniye cinsinden consumer brokerinde herhangi bir kayıt bulunamazsa bekleyeceği zamandır.
            if(consumerRecords.count()==0){
                noMessageFound++;
                if(noMessageFound>IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT){
                    break;
                }
                else continue;

            }
                consumerRecords.forEach(record ->{
                    System.out.print("CONSUMER --> RECİEVED Key: "+record.key());
                    System.out.print("Record Value: "+record.value());
                    System.out.print("Record Partition: "+record.partition());
                    System.out.println("Record Offset: "+record.offset());
                });
            //Broker için offset kaydeder
            consumer.commitAsync();
        }
        consumer.close();
    }
    protected static void runProducer()
    {
        Producer<String, String> producer= (Producer<String, String>) ProducurCreator.creatProducer();
        for (int i=0;i<IKafkaConstants.MESSAGE_COUNT;i++){

            ProducerRecord<String ,String> record=new ProducerRecord<String, String>(IKafkaConstants.TOPIK_NAME," Kayıt sayısı"+i);

            try {

                RecordMetadata metadata=producer.send(record).get();
                System.out.println("Record key "+i+"  patition "+metadata.partition()+" offset "+metadata.offset());


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }


    }
}
