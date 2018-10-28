package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.CustomerExtDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.pojo.Customer;
import cn.itcast.pojo.CustomerExt;
import cn.itcast.pojo.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author abo
 * @title SpringDataJpaOneToOne
 * @ProjectName springdatajpa_demo
 * @Description 一对多表的关系
 * @CreateDate 2018/10/25-15:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataJpaOneToMany {
    @Autowired
    private CustomerDao cd;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 1.保存客户以及客户下的联系人 1对N
     * //结论: 表达关系时通常都是通过多的一方来表达(执行的SQL语句少,不需要额外的Update)
     */
    @Test
    @Transactional
    @Commit
    public void fn1() {
        //1.创建客户
        Customer customer = new Customer();
        customer.setCustName("老大");
        //2.创建联系人
        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("小二");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("小三");
        //3.指定1对n关系  会产生多余的更新操作
        customer.getLinkMen().add(linkMan1);
        customer.getLinkMen().add(linkMan2);
        //多对1关系
        linkMan1.setCustomer(customer);
        linkMan2.setCustomer(customer);
        //4.保存客户和联系人
        //cd.save(customer);
        linkManDao.save(linkMan1);
        linkManDao.save(linkMan2); //使用了级联操作 就可以省去联系人信息维护
    }

    /**
     * 2.删除客户以及客户下的联系人 1对N
     * 不推荐使用级联删除 最多使用级联保存
     */
    @Test
    @Transactional
    @Commit
    public void fn2() {
        //1.查询客户
        Customer customer = cd.findOne(23L);
        //2.删除客户和联系人
        cd.delete(customer);
    }
    /**
     * 3.解除客户以及联系人的关联
     */
    @Test
    @Transactional
    @Commit
    public void fn3() {
        //1.查询联系人
        LinkMan lk = linkManDao.findOne(11L);
        //2.解除客户和联系人
       lk.setCustomer(null);
       linkManDao.save(lk);
    }

    /**
     * 4.创建客户以及联系人的关联
     */
    @Test
    @Transactional
    @Commit
    public void fn4() {
        //1.查询联系人
        LinkMan lk = linkManDao.findOne(11L);
        //2.获取联系人对应的客户
        Customer customer = cd.findOne(22L);
        //2.重建客户和联系人关系
        lk.setCustomer(customer);
        linkManDao.save(lk);
    }
}
