package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.pojo.Customer;
import com.mchange.v1.io.OutputStreamUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;

/**
 * @author abo
 * @title SpecQuery
 * @ProjectName springdatajpa_demo
 * @Description 多表查询
 * @CreateDate 2018/10/26-23:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecQuery {

    @Autowired
    private CustomerDao cd;

    /**
     * 根据联系人id 14对应的客户
     */
    @Test
    @Transactional
    @Commit
    public void fn1() {
        Customer customer = cd.queryCustomerById(14L);
        System.out.println(customer);
    }

    /**
     * 使用specifications多条件查询
     */
    @Test
    @Transactional
    @Commit
    public void fn2() {
        Customer customer = cd.findOne((root, query, cb) -> {
            //获得关联关系对象
            Join<Object, Object> linkMen = root.join("linkMen");
            //使用关联关系对象中的属性查询
            return cb.equal(linkMen.get("lkmId").as(Long.class), 14L);
        });
        System.out.println(customer);
    }
}
