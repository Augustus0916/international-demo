package ljh.augustus.demoproducer.repository;

import ljh.augustus.demoproducer.entity.ProducerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerInfo,Integer>, JpaSpecificationExecutor<ProducerInfo> {

}

