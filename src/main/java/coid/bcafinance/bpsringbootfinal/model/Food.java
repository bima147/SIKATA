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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MstFood")
public class Food {
    @Id
    @Column(name = "MenuID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long menuID;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "CateringID")
    public Catering cateringID;
    @Column(name = "Name", nullable = false)
    public String name;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "Country", columnDefinition = "Bigint default 79")
    public Countries country;
    /**
     * Start Group Audit Trails
     */
    @Column(name = "CreatedBy", nullable = false)
    public Long createdBy = 1L;
    @Column(name = "UpdatedBy", nullable = true)
    public Long updatedBy;
    @Column(name = "CreatedAt", columnDefinition = "Datetime default GETDATE()", nullable = true)
    public Date createdAt;
    @Column(name = "UpdatedAt", columnDefinition = "Datetime")
    public Date updatedAt;
    /**
     * End Group Audit Trails
     */

    public Long getMenuID() {
        return menuID;
    }

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public Catering getCateringID() {
        return cateringID;
    }

    public void setCateringID(Catering cateringID) {
        this.cateringID = cateringID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
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
