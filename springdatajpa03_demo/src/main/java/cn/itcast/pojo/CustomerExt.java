package cn.itcast.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author abo
 * @title CustomerExt
 * @ProjectName springdatajpa_demo
 * @Description  客户详情类
 * @CreateDate 2018/10/25-15:35
 */
@Entity
@Table(name = "cst_customer_ext")
public class CustomerExt implements Serializable {
    @Id
    @Column(name = "custExt_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custExtId;
    @Column
    private String ext;
    //一对一关系
    //mappedBy->对方引用我的属性名
    @OneToOne(mappedBy = "ext")
    private Customer customer;

    public Long getCustExtId() {
        return custExtId;
    }

    public void setCustExtId(Long custExtId) {
        this.custExtId = custExtId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
