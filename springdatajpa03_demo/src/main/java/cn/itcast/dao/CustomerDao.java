package cn.itcast.dao;

import cn.itcast.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author abo
 * @title CustomerDao
 * @ProjectName springdatajpa_demo
 * @Description TODO
 * @CreateDate 2018/10/25-15:52
 */
public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {

    @Query("select c from Customer c inner join c.linkMen lm where lm.lkmId =?1")
    Customer queryCustomerById(Long lmk_id);
}
