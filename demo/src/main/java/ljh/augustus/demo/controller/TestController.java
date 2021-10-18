package ljh.augustus.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ljh.augustus.demo.req.TestBeanReq;
import ljh.augustus.demo.res.TestBeanRes;
import ljh.augustus.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api("测试控制层")
public class TestController {

    @Autowired
    private TestService testService;

    @Value("${spring.datasource.url}")
    private String url;

    @RequestMapping(value = "/ljh/augustus/testDemo", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试")
    public List<TestBeanRes> testDemo(@RequestHeader Map<String,String> headsMap, @RequestBody TestBeanReq reqBody) throws Exception {

        List<TestBeanRes> res = testService.find(reqBody);
        return res;
    }

    @RequestMapping(value = "/ljh/augustus/test", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation("测试")
    public String test(@RequestHeader Map<String,String> headsMap, @RequestBody TestBeanReq reqBody) throws Exception {
        String test = "";
        return url;
    }
}