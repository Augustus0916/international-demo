package ljh.augustus.demokafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Future;

@Component
public class GatewayProducer {

    private static String topicName1 = "first";
    private static String topicName2 = "second";

    private static Producer<String, String> producer1;
    private static Producer<String, String> producer2;

    private GatewayProducer() {
    }

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer1 = new KafkaProducer<String, String>(props);
    }
    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer2 = new KafkaProducer<String, String>(props);
    }

    public static void sendMsgToTopic1(String headerValue) {
        Future future = producer1.send(new ProducerRecord<String, String>(topicName1, headerValue));
    }
    public static void sendMsgToTopic2(String headerValue) {
        Future future = producer2.send(new ProducerRecord<String, String>(topicName2, headerValue));
    }

    public static void closeKafkaProducer1() {
        producer1.close();
    }
    public static void closeKafkaProducer2() {
        producer2.close();
    }

}
