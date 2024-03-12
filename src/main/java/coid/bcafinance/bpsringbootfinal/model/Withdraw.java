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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "MstWithdraws")
public class Withdraw {
    @Id
    @Column(name = "WithdrawID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long withdrawID;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "BankID")
    public Bank bankID;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "CateringID")
    public Catering cateringID;
    @NotNull
    @Column(name = "Amount")
    public Float amount;
    @NotNull
    @Column(name = "Status")
    public Integer status;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "CreatedBy")
    public User createdBy;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "UpdatedBy")
    public User updatedBy;
    @Column(name = "CreatedAt", columnDefinition = "Datetime default GETDATE()")
    public Date createdAt;
    @Column(name = "UpdatedAt", columnDefinition = "Datetime")
    public Date updatedAt;

    public Long getWithdrawID() {
        return withdrawID;
    }

    public void setWithdrawID(Long withdrawID) {
        this.withdrawID = withdrawID;
    }

    public Bank getBankID() {
        return bankID;
    }

    public void setBankID(Bank bankID) {
        this.bankID = bankID;
    }

    public Catering getCateringID() {
        return cateringID;
    }

    public void setCateringID(Catering cateringID) {
        this.cateringID = cateringID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
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