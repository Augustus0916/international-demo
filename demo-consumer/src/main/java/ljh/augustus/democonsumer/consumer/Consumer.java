package ljh.augustus.democonsumer.consumer;

import com.google.gson.Gson;
import ljh.augustus.democonsumer.dto.dtoReq.ConsumerDtoReq;
import ljh.augustus.democonsumer.dto.dtoRes.ConsumerDtoRes;
import ljh.augustus.democonsumer.vo.voReq.ConsumerVoReq;
import ljh.augustus.democonsumer.vo.voRes.ConsumerVoRes;
import ljh.augustus.democonsumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private ConsumerService consumerService;

    @KafkaListener(topics = {"topic1"})
    public ConsumerVoRes message(ConsumerRecord<?, ?> record) throws Exception{

        log.info("message from consumer: " + record.value());
        ConsumerVoReq voReq = new Gson().fromJson(record.value().toString(), ConsumerVoReq.class);
        log.info("voReq: " + voReq.toString());
        ConsumerDtoReq reqDto = new ConsumerDtoReq();
        ConsumerDtoRes resDto = consumerService.consumer(reqDto);
        ConsumerVoRes voRes = new ConsumerVoRes();
        resDto.setConsumerDtoRes(voRes.getCommunity());
        return voRes;
    }
}
