package ljh.augustus.demofeign.controller;

import ljh.augustus.demoapi.feign.ProducerFeign;
import ljh.augustus.demoapi.to.toReq.ProducerToReq;
import ljh.augustus.demoapi.to.toRes.toResList.ProducerToResList;
import ljh.augustus.demofeign.vo.voReq.TestFeignVoReq;
import ljh.augustus.demofeign.vo.voRes.TestFeignVoRes;
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
    public TestFeignVoRes testFeign(@RequestHeader Map<String,String> headsMap, @RequestBody TestFeignVoReq voReq) throws Exception {

        ProducerToReq producerToReq = new ProducerToReq();
        log.info(voReq.getTestFeignVoReq());
        if(voReq.getTestFeignVoReq().equals("find")) {
            producerToReq.setBlock("朝阳");
            producerToReq.setCity("北京");
            producerToReq.setType("住宅");
            producerToReq.setZone("安贞");
        }

        Map<String,String> heads = new HashMap<>();
        /*headsMap不能直接用feign传输，会报too many byts的错。
        conroller收到请求头是map{"test":"test"}计算的content-length是"test"，如果直接传输，feign收到的请求头就是map{"map":"map{"test":"test"}}
        所以必须从新定义请求头
        */
        heads.put("test","test");

        ProducerToResList producerToResList = producerFeign.testProducer(heads, producerToReq);
        log.info(producerToResList.toString());

        TestFeignVoRes voRes = new TestFeignVoRes();
        if(producerToResList.getProducerToResList() != null)
            voRes.setTestFeignVoRes("success");
        else
            voRes.setTestFeignVoRes("fail");
        return voRes;
    }

}
