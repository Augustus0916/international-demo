package ljh.augustus.demoproducer.dao.repository;

import ljh.augustus.demoproducer.bo.po1.ProducerPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerPo,Integer>, JpaSpecificationExecutor<ProducerPo> {

}

