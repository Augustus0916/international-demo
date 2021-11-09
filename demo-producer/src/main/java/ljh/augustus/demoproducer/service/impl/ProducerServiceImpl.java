package ljh.augustus.demoproducer.service.impl;

import ljh.augustus.demoproducer.dto.dtoReq.ProducerDtoReq;
import ljh.augustus.demoproducer.dto.dtoRes.ProducerDtoRes;
import ljh.augustus.demoproducer.dto.dtoRes.dtoResList.ProducerDtoResList;
import ljh.augustus.demoproducer.bo.po1.ProducerPo;
import ljh.augustus.demoproducer.dao.repository.ProducerRepository;
import ljh.augustus.demoproducer.service.ProducerService;
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
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public ProducerDtoResList find(ProducerDtoReq dtoReq) throws Exception {
        ProducerDtoResList dtoResList = new ProducerDtoResList();
        Specification<ProducerPo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (dtoReq.getCity() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("city"), dtoReq.getCity()));
            }
            if (dtoReq.getBlock() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("block"), dtoReq.getBlock()));
            }
            if (dtoReq.getType() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("type"), dtoReq.getType()));
            }
            if (dtoReq.getZone() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("zone"), dtoReq.getZone()));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        };

        List<ProducerPo> list = producerRepository.findAll(specification);

        List<ProducerDtoRes> dtoRess = new LinkedList<>();
        for(ProducerPo info : list) {
            ProducerDtoRes dtoRes = new ProducerDtoRes();
            dtoRes.setCommunity(info.getCommunity());
            dtoRes.setCost(info.getCost());
            dtoRess.add(dtoRes);
        }
        dtoResList.setProducerDtoResList(dtoRess);

        return dtoResList;
    }

}