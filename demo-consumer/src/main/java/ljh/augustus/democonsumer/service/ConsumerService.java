package ljh.augustus.democonsumer.service;

import ljh.augustus.democonsumer.dto.dtoReq.ConsumerDtoReq;
import ljh.augustus.democonsumer.dto.dtoRes.ConsumerDtoRes;

public interface ConsumerService {
    ConsumerDtoRes consumer(ConsumerDtoReq req) throws Exception;
}
