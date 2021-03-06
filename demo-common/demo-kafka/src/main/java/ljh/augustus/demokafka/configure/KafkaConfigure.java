package ljh.augustus.demokafka.configure;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigure {

    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("testtopic",2, (short) 1 );
    }
    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("testtopic",2, (short) 1 );
    }

}
