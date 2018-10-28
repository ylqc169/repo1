package cn.itcast.dao;

import cn.itcast.pojo.Customer;
import cn.itcast.pojo.CustomerExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author abo
 * @title CustomerDao
 * @ProjectName springdatajpa_demo
 * @Description TODO
 * @CreateDate 2018/10/25-15:52
 */
public interface CustomerExtDao extends JpaRepository<CustomerExt,Long>,JpaSpecificationExecutor<CustomerExt> {

}
