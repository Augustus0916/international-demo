package ljh.augustus.demo.service;

import ljh.augustus.demo.req.TestBeanReq;
import ljh.augustus.demo.res.TestBeanRes;

import java.util.List;

public interface TestService {
    List<TestBeanRes> find(TestBeanReq testBeanReq) throws Exception;
}