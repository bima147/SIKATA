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

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "MstUsers", indexes = {
        @Index(name = "idx_email_mstuser", columnList = "email")
}, uniqueConstraints = @UniqueConstraint(columnNames = {
        "email"
}, name = "unx_email_mstuser"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID", nullable = true)
    private Long userID;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Profile", nullable = true)
    private String profile;
    @Column(name = "Gender", nullable = false)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "access", foreignKey = @ForeignKey(name = "FK_TO_AKSES"))
    private Access access;

    @Column(name = "IsActive", columnDefinition = "Bit default '1'") //08577206
    private Boolean isActive;
    @Column(name = "Phone", nullable = false, unique = true)
    private String phone;
    @Column(name = "PhoneVerifiedAt", nullable = true)
    private String phoneVerifiedAt;
    @Column(name = "Username", nullable = false)
    private String username;
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    @Column(name = "EmailVerifiedAt", nullable = true)
    private Date emailVerifiedAt;
    @Column(name = "Token")
    private String token;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "CreatedBy", nullable = false)
    private Long createdBy = 1L;
    @Column(name = "UpdatedtBy")
    private Long updatedBy;
    @Column(name = "CreatedAt", columnDefinition = "Datetime default GETDATE()")
    public Date createdAt;
    @Column(name = "UpdatedAt", columnDefinition = "Datetime")
    public Date updatedAt;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneVerifiedAt() {
        return phoneVerifiedAt;
    }

    public void setPhoneVerifiedAt(String phoneVerifiedAt) {
        this.phoneVerifiedAt = phoneVerifiedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Date emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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