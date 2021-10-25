package ljh.augustus.demoproducer.service;

import ljh.augustus.demoproducer.req.TestBeanReq;
import ljh.augustus.demoproducer.res.TestBeanRes;

import java.util.List;

public interface TestService {
    List<TestBeanRes> find(TestBeanReq testBeanReq) throws Exception;
}