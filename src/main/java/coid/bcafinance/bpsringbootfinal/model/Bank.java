package coid.bcafinance.bpsringbootfinal.model;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import org.springframework.lang.Nullable;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MstBanks")
public class Bank {
    @Id
    @Column(name = "BankID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bankID;
    @Column(name = "Name", nullable = false)
    public String name;
    @Column(name = "AccountNumber", nullable = false)
    public String accountNumber;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "cateringID")
    public Catering cateringID;
    /**
     * Start Group Audit Trails
     */
    @Column(name = "CreatedBy", nullable = false)
    public Long createdBy = 1L;
    @Column(name = "UpdatedBy", nullable = true)
    public Long updatedBy;
    @Column(name = "CreatedAt", columnDefinition = "Datetime default GETDATE()", nullable = true)
    public Date createdAt;
    @Column(name = "UpdatedAt", columnDefinition = "Datetime", nullable = true)
    public Date updatedAt;
    /**
     * End Group Audit Trails
     */

    public Long getBankID() {
        return bankID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Catering getCateringID() {
        return cateringID;
    }

    public void setCateringID(Catering cateringID) {
        this.cateringID = cateringID;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}