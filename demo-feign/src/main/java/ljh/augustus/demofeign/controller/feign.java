package ljh.augustus.demofeign.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ljh.augustus.demoapi.feign.ProducerFeign;
import ljh.augustus.demoapi.to.toReq.ProducerToReq;
import ljh.augustus.demoapi.to.toRes.toResList.ProducerToResList;
import ljh.augustus.demofeign.vo.voReq.TestFeignVoReq;
import ljh.augustus.demofeign.vo.voRes.TestFeignVoRes;
import ljh.augustus.demokafka.consumer.FeignConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Api("测试feign控制层")
public class feign {

    @Autowired
    private ProducerFeign producerFeign;

    @RequestMapping(value = "/ljh/augustus/testFeign", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试testFeign")
    public TestFeignVoRes testFeign(@RequestHeader Map<String,String> headsMap, @RequestBody TestFeignVoReq voReq) throws Exception {

        ProducerToReq producerToReq = new ProducerToReq();
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
        String session = "";
        Consumer<String, String> consumer = FeignConsumer.getFeignConsumer();
        Duration durationSeconds = Duration.ofSeconds(3);
        ConsumerRecords<String, String> records = consumer.poll(durationSeconds);
        for (ConsumerRecord<String, String> record : records) {
            log.info("record.value() in feign: " + record.value());
            session = record.value();
        }
        log.info("session: " + session);
        heads.put("session", session);

        TestFeignVoRes voRes = new TestFeignVoRes();

        ProducerToResList producerToResList = producerFeign.testProducer(heads, producerToReq);
        if(producerToResList.getProducerToResList() != null)
            voRes.setTestFeignVoRes("success");
        else
            voRes.setTestFeignVoRes("fail");
        return voRes;
    }



}
