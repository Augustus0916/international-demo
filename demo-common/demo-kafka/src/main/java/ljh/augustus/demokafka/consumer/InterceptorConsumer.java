package ljh.augustus.demokafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
@Slf4j
public class InterceptorConsumer {

    private static String topicName = "second";
    private static Consumer<String, String> consumer;

    private InterceptorConsumer() {
    }

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "ljh.augustus2");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton(topicName));
    }

    public static Consumer<String, String> getInterceptorConsumer() {
        return consumer;
    }

    public static void closeInterceptorConsumer1() {
        consumer.close();
    }

    public static void getMsgFromInterceptorConsumer(){
        Duration durationSeconds = Duration.ofSeconds(3);
        while(true){
            ConsumerRecords<String, String> records = InterceptorConsumer.getInterceptorConsumer().poll(durationSeconds);
            if (records.count() > 0) {
                for (ConsumerRecord<String, String> record : records) {
                    log.info("record.value() in InterceptorConsumer: " + record.value());
                }
            }
        }
    }
}
