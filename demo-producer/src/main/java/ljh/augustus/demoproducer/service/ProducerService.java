package ljh.augustus.demoproducer.service;

import ljh.augustus.demoproducer.dto.reqDto.ProducerReqDto;
import ljh.augustus.demoproducer.dto.resDto.resDtoList.ProducerResDtoList;

public interface ProducerService {
    ProducerResDtoList find(ProducerReqDto reqDto) throws Exception;
}