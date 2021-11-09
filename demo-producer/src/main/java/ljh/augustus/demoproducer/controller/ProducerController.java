package ljh.augustus.demoproducer.controller;

import com.google.gson.Gson;
import ljh.augustus.demoapi.to.toReq.ProducerToReq;
import ljh.augustus.demoapi.to.toRes.ProducerToRes;
import ljh.augustus.demoapi.to.toRes.toResList.ProducerToResList;
import ljh.augustus.demoproducer.dto.dtoReq.ProducerDtoReq;
import ljh.augustus.demoproducer.dto.dtoRes.ProducerDtoRes;
import ljh.augustus.demoproducer.dto.dtoRes.dtoResList.ProducerDtoResList;
import ljh.augustus.demoproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/ljh/augustus/testProducer", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    public ProducerToResList testProducer(@RequestHeader Map<String,String> headsMap, @RequestBody ProducerToReq toReq) throws Exception {
        log.info("here");

        ProducerDtoReq reqDto = new ProducerDtoReq();
        reqDto.setCity(toReq.getCity());
        reqDto.setBlock(toReq.getBlock());
        reqDto.setType(toReq.getType());
        reqDto.setZone(toReq.getZone());

        ProducerDtoResList dtoResList = producerService.find(reqDto);

        List<ProducerDtoRes> dtoRess = dtoResList.getProducerDtoResList();
        List<ProducerToRes> toRess = new LinkedList<>();
        for(ProducerDtoRes dtoRes : dtoRess) {
            ProducerToRes toRes = new ProducerToRes();
            toRes.setCommunity(dtoRes.getCommunity());
            toRes.setCost(dtoRes.getCost());
            toRess.add(toRes);
        }
        ProducerToResList toResList = new ProducerToResList();
        toResList.setProducerToResList(toRess);

        String message = new Gson().toJson(toReq);
        log.info("message from producer: " + message);
        kafkaTemplate.send("topic1", message);
        return toResList;
    }
}
