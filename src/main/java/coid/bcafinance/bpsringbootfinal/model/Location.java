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
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "MstLocations")
public class Location implements Serializable {
    @Id
    @Column(name = "LocationID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long locationID;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "CateringID")
    public Catering cateringID;
    @Column(name = "Latitude", nullable = false)
    public String latitude;
    @Column(name = "Longitude", nullable = false)
    public String longitude;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "Country", columnDefinition = "Bigint default 79")
    public Countries country;
    @Column(name = "Province", nullable = false)
    public String province;
    @Column(name = "City", nullable = false)
    public String city;
    @Column(name = "Limit")
    public Integer limit;
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

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public Catering getCateringID() {
        return cateringID;
    }

    public void setCateringID(Catering cateringID) {
        this.cateringID = cateringID;
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

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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
