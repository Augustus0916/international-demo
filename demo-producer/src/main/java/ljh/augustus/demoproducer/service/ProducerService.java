package ljh.augustus.demoproducer.service;

import ljh.augustus.demoproducer.dto.dtoReq.ProducerDtoReq;
import ljh.augustus.demoproducer.dto.dtoRes.dtoResList.ProducerDtoResList;

public interface ProducerService {
    ProducerDtoResList find(ProducerDtoReq dtoReq) throws Exception;
}