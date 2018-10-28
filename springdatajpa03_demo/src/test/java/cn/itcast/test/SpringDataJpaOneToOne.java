package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.CustomerExtDao;
import cn.itcast.pojo.Customer;
import cn.itcast.pojo.CustomerExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author abo
 * @title SpringDataJpaOneToOne
 * @ProjectName springdatajpa_demo
 * @Description 一对一表的关系  针对1对1的关系 A&B 外键在A中 需要先保存B再保存A
 * @CreateDate 2018/10/25-15:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataJpaOneToOne {
    @Autowired
    private CustomerDao cd;
    @Autowired
    private CustomerExtDao cet;

    /**
     * 保存客户并保存详情
     * 保存A&B的关联关系时,如果A包含B,应先保存B再保存A
     * 表达关系时,应在配置了@JoinColumn注解的一方表达
     */
    @Test
    @Transactional
    @Commit
    public void fn1() {
        //1.创建客户
        Customer customer = new Customer();
        customer.setCustName("石根硕麻辣腰子");
        //2.创建客户详情
        CustomerExt customerExt = new CustomerExt();
        customerExt.setExt("好吃不贵");
        //3.建立一对一关系
        customer.setExt(customerExt);
        //customerExt.setCustomer(customer);//浪费性能 由客户表来维护外键关系
        //4.保存客户和详情  先保存详情再保存客户
        cet.save(customerExt);
        cd.save(customer);
    }

    /**
     * 删除客户并删除客户详情
     * 需要首先删除拥有外键的客户，再删除客户详情
     */
    @Test
    @Transactional
    @Commit
    public void fn2(){
        Customer customer = cd.findOne(15L);
        //对象视图导航查询
        CustomerExt ext = customer.getExt();
        cd.delete(customer);
        cet.delete(ext);
    }
}
