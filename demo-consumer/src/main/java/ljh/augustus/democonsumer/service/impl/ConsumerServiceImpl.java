package ljh.augustus.democonsumer.service.impl;

import ljh.augustus.democonsumer.dto.reqDto.ConsumerReqDto;
import ljh.augustus.democonsumer.dto.resDto.ConsumerResDto;
import ljh.augustus.democonsumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public ConsumerResDto consumer(ConsumerReqDto reqDto) throws Exception{
        ConsumerResDto resDto = new ConsumerResDto();
        resDto.setConsumerResDto("in consumer service");
        return resDto;
    }
}
