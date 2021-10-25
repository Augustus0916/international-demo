package ljh.augustus.demoproducer.repository;

import ljh.augustus.demoproducer.entity.TestBeanInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestBeanInfo,Integer>, JpaSpecificationExecutor<TestBeanInfo> {

}

