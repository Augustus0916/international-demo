package ljh.augustus.democonsumer.consumer;

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

        log.info("message from consumer: " + record.value().toString());
        ConsumerVoReq voReq = new ConsumerVoReq();
        voReq.setConsumerVoReq(record.value().toString());
        ConsumerDtoReq reqDto = new ConsumerDtoReq();
        reqDto.setConsumerDtoReq(voReq.getConsumerVoReq());
        ConsumerDtoRes resDto = consumerService.consumer(reqDto);
        ConsumerVoRes voRes = new ConsumerVoRes();
        voRes.setConsumerVoRes(resDto.getConsumerDtoRes());
        log.info("res: " + voRes.getConsumerVoRes());
        return voRes;
    }
}
