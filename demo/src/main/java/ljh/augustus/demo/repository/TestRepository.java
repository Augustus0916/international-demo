package ljh.augustus.demo.repository;

import ljh.augustus.demo.entity.TestBeanInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestBeanInfo,Integer>, JpaSpecificationExecutor<TestBeanInfo> {

}

