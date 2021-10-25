package ljh.augustus.demoproducer.service.impl;

import ljh.augustus.demoproducer.entity.TestBeanInfo;
import ljh.augustus.demoproducer.repository.TestRepository;
import ljh.augustus.demoproducer.req.TestBeanReq;
import ljh.augustus.demoproducer.res.TestBeanRes;
import ljh.augustus.demoproducer.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestBeanRes> find(TestBeanReq testBeanReq) throws Exception {
        List<TestBeanRes> result = new LinkedList<>();
        Specification<TestBeanInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (testBeanReq.getCity() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("city"), testBeanReq.getCity()));
            }
            if (testBeanReq.getBlock() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("block"), testBeanReq.getBlock()));
            }
            if (testBeanReq.getType() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("type"), testBeanReq.getType()));
            }
            if (testBeanReq.getZone() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("zone"), testBeanReq.getZone()));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        };
        log.info("sp: " + specification);
        List<TestBeanInfo> list = testRepository.findAll(specification);
        for(TestBeanInfo info : list) {
            TestBeanRes res = new TestBeanRes();
            res.setCommunity(info.getCommunity());
            res.setCost(info.getCost());
            result.add(res);
        }
        log.info("查询结果： " + result);
        return result;
    }

}