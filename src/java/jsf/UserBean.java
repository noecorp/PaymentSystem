/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import ejb.UserServiceModel;
import entity.SystemUser;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author Rhayan
 */
@Named
@RequestScoped
public class UserBean {
    
    private Long id;
    private SystemUser user;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private BigDecimal balance;
    private String currency;
    private Date registrationDate;
    private Date updateDate;
    final static Logger myLogger = Logger.getLogger("javax.enterprise.resource.webcontainer.jsf");
    
    @EJB
    private UserServiceModel userStore;
    
    public UserBean() {
        user = new SystemUser();
    }

     public String saveCustomer() {
        String returnValue = "customer_saved";
        
        try {
            populateCustomer();
            userStore.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "error_saving_customer";
        }
        
        return returnValue;
    }
    
    private void populateCustomer() {
        if (user == null) {
            user = new SystemUser();
        }
        user.setFirstname(getFirstname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
    }
       
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String search(){
        user = userStore.getUser(this.id);
        
        return "showuser";
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }
    
    
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("UserBean: PostConstruct");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("UserBean: PreDestroy");
    }
}
