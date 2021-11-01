package ljh.augustus.demoapi.feign;

import ljh.augustus.demoapi.req.ProducerReq;
import ljh.augustus.demoapi.res.resList.ProducerResList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

@Component
@FeignClient(value = "demo-producer")
public interface ProducerFeign {

    @RequestMapping(value = "/ljh/augustus/testProducer", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    ProducerResList testProducer(@RequestHeader Map<String, String> headsMap, @RequestBody ProducerReq req) throws Exception;

}
