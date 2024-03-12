package coid.bcafinance.bpsringbootfinal.model;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 06/03/2024 13:40
@Last Modified 06/03/2024 13:40
Version 1.0
*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MstTransactionApproval")
public class TransactionApproval {
    @Id
    @Column(name = "TransactionApprovalID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long transactioApprovalID;
    @Column(name = "TransactionNumber", nullable = false)
    public String transactionNumber;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "LocationID", nullable = false)
    public Location locationID;
    @Column(name = "PaymentMethod", nullable = false)
    public String paymentMethod;
    @Column(name = "Amount", columnDefinition = "Money", nullable = false)
    public Float amount;
    @Column(name = "Status", columnDefinition = "Varchar(255) default 'Waiting'", nullable = false)
    public String status;
    @Column(name = "Latitude", nullable = false)
    public String latitude;
    @Column(name = "Longitude", nullable = false)
    public String longitude;
    @Column(name = "StartDate", nullable = false)
    public Date startDate;
    @Column(name = "EndDate")
    public Date endDate;
    @Column(name = "IsApproved", columnDefinition = "Bit default 0")
    public Boolean isApproved;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ApprovedBy")
    public User approvedBy;
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

    public Long getTransactioApprovalID() {
        return transactioApprovalID;
    }

    public void setTransactioApprovalID(Long transactioApprovalID) {
        this.transactioApprovalID = transactioApprovalID;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Location getLocationID() {
        return locationID;
    }

    public void setLocationID(Location locationID) {
        this.locationID = locationID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
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
