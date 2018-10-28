package cn.itcast.pojo;

import javax.naming.Name;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abo
 * @title User
 * @ProjectName springdatajpa_demo
 * @Description TODO
 * @CreateDate 2018/10/25-18:08
 */
@Entity
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "user_id")
    private long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST)
    //中间表配置
    //name：中间表表名
    //joinColumns:与我相关的关联配置
    //inverseJoinColumns:对方的关联配置
    @JoinTable(name = "user_role_rel",
            joinColumns = {@JoinColumn(name = "u_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id",referencedColumnName = "role_id")})
    private Set<Role> roles=new HashSet<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
