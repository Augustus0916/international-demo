package ljh.augustus.demofeign.controller;

import ljh.augustus.demoapi.feign.ProducerFeign;
import ljh.augustus.demoapi.req.ProducerReq;
import ljh.augustus.demofeign.req.TestFeignReq;
import ljh.augustus.demofeign.res.TestFeignRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class feign {

    @Autowired
    private ProducerFeign producerFeign;

    @RequestMapping(value = "/ljh/augustus/testFeign", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    public TestFeignRes testFeign(@RequestHeader Map<String,String> headsMap, @RequestBody TestFeignReq req) throws Exception {

        ProducerReq producerReq = new ProducerReq();
        Map<String,String> heads = new HashMap<>();
        /*headsMap不能直接用feign传输，会报too many byts的错。
        conroller收到请求头是map{"test":"test"}计算的content-length是"test"，如果直接传输，feign收到的请求头就是map{"map":"map{"test":"test"}}
        所以必须从新定义请求头
        */
        heads.put("test","test");

        producerFeign.testProducer(heads, producerReq);
        TestFeignRes res = new TestFeignRes();
        return res;
    }

}
