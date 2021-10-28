package ljh.augustus.demoproducer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ljh.augustus.demoproducer.dto.reqDto.ProducerReqDto;
import ljh.augustus.demoproducer.dto.resDto.ProducerResDto;
import ljh.augustus.demoproducer.dto.resDto.resDtoList.ProducerResDtoList;
import ljh.augustus.demoproducer.req.ProducerReq;
import ljh.augustus.demoproducer.res.ProducerRes;
import ljh.augustus.demoproducer.res.resList.ProducerResList;
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

    @RequestMapping(value = "/ljh/augustus/testProducer", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试testProducer")
    public ProducerResList testProducer(@RequestHeader Map<String,String> headsMap, @RequestBody ProducerReq req) throws Exception {

        ProducerReqDto reqDto = new ProducerReqDto();
        reqDto.setCity(req.getCity());
        reqDto.setBlock(req.getBlock());
        reqDto.setType(req.getType());
        reqDto.setZone(req.getZone());

        ProducerResDtoList resDtoList = producerService.find(reqDto);

        List<ProducerResDto> resDtos = resDtoList.getProducerResList();
        List<ProducerRes> ress = new LinkedList<>();
        for(ProducerResDto resDto : resDtos) {
            ProducerRes res = new ProducerRes();
            res.setCommunity(resDto.getCommunity());
            res.setCost(resDto.getCost());
            ress.add(res);
        }
        ProducerResList resList = new ProducerResList();
        resList.setProducerResList(ress);

        log.info("message from producer: " + "city: " + req.getCity() + " block: " + req.getBlock() + " zone: " + req.getZone() + " zone: " + req.getType());
        kafkaTemplate.send("topic1", "city: " + req.getCity() + " block: " + req.getBlock() + " zone: " + req.getZone() + " zone: " + req.getType());
        log.info("message from producer: " + req);
        return resList;
    }
}
