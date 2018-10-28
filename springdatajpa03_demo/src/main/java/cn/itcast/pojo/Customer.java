package cn.itcast.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abo
 * @title Customer
 * @ProjectName springdatajpa
 * @Description: Customer 客户实体类定义
 * @CreateDate 2018-10-22 15:53
 */
/*
  cust_id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  cust_name varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  cust_source varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  cust_industry varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  cust_level varchar(32) DEFAULT NULL COMMENT '客户级别',
  cust_address varchar(128) DEFAULT NULL COMMENT '客户联系地址',
  cust_phone varchar(64) DEFAULT NULL COMMENT '客户联系电话',
 */
@Entity
@Table(name = "cst_customer")
public class Customer implements Serializable{
    @Id
    @Column(name = "cust_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;
    @Column(name = "cust_industry")
    private String custIndustry;
    @Column(name = "cust_level")
    private String custLevel;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "cust_phone")
    private String custPhone;
    //一对一注解
    @OneToOne
    //表达外键的注解
    @JoinColumn(name = "ext_id",referencedColumnName = "custExt_Id")
    private CustomerExt ext;
    //与联系人的一对多关系
    /**
     * CascadeType.PERSIST :级联保存
     * CascadeType.REMOVE  :级联删除
     * CascadeType.ALL     ：级联保存和删除
     *
     *fetch : 指定关联级别的加载策略
     *FetchType.EAGER:立即加载
     *FetchType.LAZY : 延迟加载(默认值)
     */
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})//,fetch = FetchType.LAZY
    //name 填写外键列列名  referencedColumnName引用的主键列列名
    //@JoinColumn(name ="lkm_cust_id" ,referencedColumnName = "cust_id") 客户表不进行外键的配置，避免更新操作
    private Set<LinkMan> linkMen = new HashSet<>();

    public Set<LinkMan> getLinkMen() {
        return linkMen;
    }

    public void setLinkMen(Set<LinkMan> linkMen) {
        this.linkMen = linkMen;
    }

    public CustomerExt getExt() {
        return ext;
    }

    public void setExt(CustomerExt ext) {
        this.ext = ext;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                '}';
    }
}
