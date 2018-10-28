package cn.itcast.dao;

import cn.itcast.pojo.Role;
import cn.itcast.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author abo
 * @title CustomerDao
 * @ProjectName springdatajpa_demo
 * @Description TODO
 * @CreateDate 2018/10/25-15:52
 */
public interface RoleDao extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {

}
