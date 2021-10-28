package ljh.augustus.demoproducer.service.impl;

import ljh.augustus.demoproducer.dto.reqDto.ProducerReqDto;
import ljh.augustus.demoproducer.dto.resDto.ProducerResDto;
import ljh.augustus.demoproducer.dto.resDto.resDtoList.ProducerResDtoList;
import ljh.augustus.demoproducer.entity.ProducerInfo;
import ljh.augustus.demoproducer.repository.ProducerRepository;
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
    public ProducerResDtoList find(ProducerReqDto reqDto) throws Exception {
        ProducerResDtoList resDtoList = new ProducerResDtoList();
        Specification<ProducerInfo> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (reqDto.getCity() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("city"), reqDto.getCity()));
            }
            if (reqDto.getBlock() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("block"), reqDto.getBlock()));
            }
            if (reqDto.getType() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("type"), reqDto.getType()));
            }
            if (reqDto.getZone() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("zone"), reqDto.getZone()));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        };

        List<ProducerInfo> list = producerRepository.findAll(specification);

        List<ProducerResDto> resDtos = new LinkedList<>();
        for(ProducerInfo info : list) {
            ProducerResDto resDto = new ProducerResDto();
            resDto.setCommunity(info.getCommunity());
            resDto.setCost(info.getCost());
            resDtos.add(resDto);
        }
        resDtoList.setProducerResList(resDtos);

        return resDtoList;
    }

}