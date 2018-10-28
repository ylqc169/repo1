package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.pojo.Customer;
import cn.itcast.pojo.LinkMan;
import cn.itcast.pojo.Role;
import cn.itcast.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author abo
 * @title SpringDataJpaOneToOne
 * @ProjectName springdatajpa_demo
 * @Description 多对多表的关系
 * @CreateDate 2018/10/25-15:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataJpaManyToMany {
    @Autowired
    private UserDao ud;
    @Autowired
    private RoleDao rd;

    /**
     * 保存用户以及角色
     */
    @Test
    @Transactional
    @Commit
    public void fn1() {
        User u1 = new User();
        u1.setUsername("熊大");
        User u2 = new User();
        u2.setUsername("熊二");

        Role r1  = new Role();
        r1.setRoleName("经理");
        Role r2  = new Role();
        r2.setRoleName("职员");

        u1.getRoles().add(r1);
        u1.getRoles().add(r2);
        u2.getRoles().add(r1);
        u2.getRoles().add(r2);

        ud.save(u1);
        ud.save(u2);

/*        rd.save(r1);
        rd.save(r2);*/ //可修改为级联

    }


    /**
     * 删除用户
     */
    @Test
    @Transactional
    @Commit
    public void fn2() {
        ud.delete(4L);
    }

    /**
     * 解除用户角色关系
     */
    @Test
    @Transactional
    @Commit
    public void fn3() {
        //1.获取要操作的用户
        User user = ud.findOne(3L);
        //2.获取要移除的角色
        Role r1 = rd.findOne(3L);
        //3.表达关系
        user.getRoles().remove(r1);
        //4.保存
        ud.save(user);
    }

    /**
     * 重建用户角色关系
     */
    @Test
    @Transactional
    @Commit
    public void fn4() {
        //1.获取要操作的用户
        User user = ud.findOne(3L);
        //2.获取要移除的角色
        Role r1 = rd.findOne(3L);
        //3.表达关系
        user.getRoles().add(r1);
        //4.保存
        ud.save(user);
    }

    @Test
    @Transactional
    @Commit
    public void fn5() {
        //1.获取要操作的用户
        User user = new User();
        user.setUsername("熊二");
        //2.获取要添加的角色
        Role r1 = rd.findOne(3L);
        Role r2 = rd.findOne(4L);
        //3.表达关系
        user.getRoles().add(r1);
        user.getRoles().add(r2);
        //4.保存
        ud.save(user);
    }
}
