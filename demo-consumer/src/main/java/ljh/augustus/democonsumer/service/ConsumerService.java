package ljh.augustus.democonsumer.service;

import ljh.augustus.democonsumer.dto.reqDto.ConsumerReqDto;
import ljh.augustus.democonsumer.dto.resDto.ConsumerResDto;

public interface ConsumerService {
    ConsumerResDto consumer(ConsumerReqDto req) throws Exception;
}
