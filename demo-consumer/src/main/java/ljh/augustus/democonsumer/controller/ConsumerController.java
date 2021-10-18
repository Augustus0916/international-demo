package ljh.augustus.democonsumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ljh.augustus.democonsumer.req.ConsumerReq;
import ljh.augustus.democonsumer.res.ConsumerRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@Slf4j
@Api("测试消费控制层")
public class ConsumerController {

    @RequestMapping(value = "/ljh/augustus/testConsumer", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试testConsumer")
    public ConsumerRes testConsumer(@RequestHeader Map<String,String> headsMap, @RequestBody ConsumerReq req) throws Exception {

        ConsumerRes res = new ConsumerRes();
        res.setConsumerRes(req.getConsumerReq());
        return res;
    }
}
