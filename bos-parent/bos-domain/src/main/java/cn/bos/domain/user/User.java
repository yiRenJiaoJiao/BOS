package cn.bos.domain.user;
// Generated 2017-7-11 17:25:29 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import cn.bos.domain.qupai.Noticebill;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="t_user"
    ,catalog="bos"
    , uniqueConstraints = {@UniqueConstraint(columnNames="EMAIL"), @UniqueConstraint(columnNames="TELEPHONE")} 
)
public class User  implements java.io.Serializable {


     private Integer id;
     private String email;
     private String password;
     private BigDecimal salary;
     private Date birthday;
     private String gender;
     private String station;
     private String telephone;
     private String remark;
     private String username;
     private Set<Noticebill> noticebills = new HashSet<Noticebill>(0);
    public User() {
    }

	
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String email, String password, BigDecimal salary, Date birthday, String gender, String station, String telephone, String remark, String username) {
       this.email = email;
       this.password = password;
       this.salary = salary;
       this.birthday = birthday;
       this.gender = gender;
       this.station = station;
       this.telephone = telephone;
       this.remark = remark;
       this.username = username;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="EMAIL", unique=true, nullable=false, length=30)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="PASSWORD", nullable=false, length=32)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="SALARY", precision=10)
    public BigDecimal getSalary() {
        return this.salary;
    }
    
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY", length=0)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="GENDER", length=10)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Column(name="STATION", length=40)
    public String getStation() {
        return this.station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }
    
    @Column(name="TELEPHONE", unique=true, length=11)
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="REMARK")
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="username")
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
    public Set<Noticebill> getNoticebills() {
        return this.noticebills;
    }
    
    public void setNoticebills(Set<Noticebill> noticebills) {
        this.noticebills = noticebills;
    }


}


