package ljh.augustus.democonsumer.consumer;

import ljh.augustus.democonsumer.dto.reqDto.ConsumerReqDto;
import ljh.augustus.democonsumer.dto.resDto.ConsumerResDto;
import ljh.augustus.democonsumer.req.ConsumerReq;
import ljh.augustus.democonsumer.res.ConsumerRes;
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
    public ConsumerRes message(ConsumerRecord<?, ?> record) throws Exception{

        log.info("message from consumer: " + record.value().toString());
        ConsumerReq req = new ConsumerReq();
        req.setConsumerReq(record.value().toString());
        ConsumerReqDto reqDto = new ConsumerReqDto();
        reqDto.setConsumerReqDto(req.getConsumerReq());
        ConsumerResDto resDto = consumerService.consumer(reqDto);
        ConsumerRes res = new ConsumerRes();
        res.setConsumerRes(resDto.getConsumerResDto());
        log.info("res: " + res.getConsumerRes());
        return res;
    }
}
