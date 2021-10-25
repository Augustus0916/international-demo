package ljh.augustus.demoproducer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ljh.augustus.demoproducer.req.ProducerReq;
import ljh.augustus.demoproducer.res.ProducerRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@Slf4j
@Api("测试生产控制层")
public class ProducerController {

    @RequestMapping(value = "/ljh/augustus/testProducer", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试testProducer")
    public ProducerRes testProducer(@RequestHeader Map<String,String> headsMap, @RequestBody ProducerReq req) throws Exception {
        log.info("test spring cloud + elasticsearch + filebeat + kibana");
        ProducerRes res = new ProducerRes();
        res.setProducerRes(req.getProducerReq());
        return res;
    }
}
