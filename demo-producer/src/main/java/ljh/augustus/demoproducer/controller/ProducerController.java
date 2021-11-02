package ljh.augustus.demoproducer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ljh.augustus.demoproducer.dto.dtoReq.ProducerDtoReq;
import ljh.augustus.demoproducer.dto.dtoRes.ProducerDtoRes;
import ljh.augustus.demoproducer.dto.dtoRes.dtoResList.ProducerDtoResList;
import ljh.augustus.demoproducer.vo.voReq.ProducerVoReq;
import ljh.augustus.demoproducer.vo.voRes.ProducerVoRes;
import ljh.augustus.demoproducer.vo.voRes.voResList.ProducerVoResList;
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
@Api("测试生产控制层")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/ljh/augustus/testGet", method = RequestMethod.POST)
    public  void testGet(){
        log.info("test test");
    }

    @RequestMapping(value = "/ljh/augustus/testProducer", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试testProducer")
    public ProducerVoResList testProducer(@RequestHeader Map<String,String> headsMap, @RequestBody ProducerVoReq voReq) throws Exception {

        ProducerDtoReq reqDto = new ProducerDtoReq();
        reqDto.setCity(voReq.getCity());
        reqDto.setBlock(voReq.getBlock());
        reqDto.setType(voReq.getType());
        reqDto.setZone(voReq.getZone());

        ProducerDtoResList dtoResList = producerService.find(reqDto);

        List<ProducerDtoRes> dtoRess = dtoResList.getProducerDtoResList();
        List<ProducerVoRes> voRess = new LinkedList<>();
        for(ProducerDtoRes dtoRes : dtoRess) {
            ProducerVoRes voRes = new ProducerVoRes();
            voRes.setCommunity(dtoRes.getCommunity());
            voRes.setCost(dtoRes.getCost());
            voRess.add(voRes);
        }
        ProducerVoResList resList = new ProducerVoResList();
        resList.setProducerVoResList(voRess);

        log.info("message from producer: " + "city: " + voReq.getCity() + " block: " + voReq.getBlock() + " zone: " + voReq.getZone() + " zone: " + voReq.getType());
        kafkaTemplate.send("topic1", "city: " + voReq.getCity() + " block: " + voReq.getBlock() + " zone: " + voReq.getZone() + " zone: " + voReq.getType());

        return resList;
    }
}
