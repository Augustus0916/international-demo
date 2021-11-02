package ljh.augustus.democonsumer.service.impl;

import ljh.augustus.democonsumer.dto.dtoReq.ConsumerDtoReq;
import ljh.augustus.democonsumer.dto.dtoRes.ConsumerDtoRes;
import ljh.augustus.democonsumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public ConsumerDtoRes consumer(ConsumerDtoReq dtoReq) throws Exception{
        ConsumerDtoRes dtoRes = new ConsumerDtoRes();
        dtoRes.setConsumerDtoRes("in consumer service");
        return dtoRes;
    }
}
