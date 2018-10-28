package cn.itcast.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abo
 * @title Role
 * @ProjectName springdatajpa_demo
 * @Description TODO
 * @CreateDate 2018/10/25-18:08
 */
@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "memo")
    private String memo;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users=new HashSet<>();

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
