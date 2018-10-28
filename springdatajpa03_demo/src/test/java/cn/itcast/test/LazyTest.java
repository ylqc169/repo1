package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.pojo.Customer;
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
 * @title LazyTest
 * @ProjectName springdatajpa_demo
 * @Description //延迟加载演示
 * @CreateDate 2018/10/26-23:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LazyTest {
    @Autowired
    private CustomerDao cd;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 延迟加载
     * 取出客户以及客户关联的联系人
     * 默认情况下,对象关系视图导航使用延迟加载
     * 默认加了  fetch = FetchType.LAZY（推荐使用）
     */
    @Test
    @Transactional
    @Commit
    public void fn1() {
        Customer customer = cd.findOne(22L);
        Set<LinkMan> linkMen = customer.getLinkMen();
        System.out.println("hehe");
        for (LinkMan linkMAN : linkMen) {
            System.out.println(linkMAN);
        }
    }
}
