package coid.bcafinance.bpsringbootfinal.dto.location;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 12/03/2024 8:47
@Last Modified 12/03/2024 8:47
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.model.Catering;
import coid.bcafinance.bpsringbootfinal.model.Countries;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class LocationDTO {
    public Long locationID;
    @NotNull(message = "ID Catering tidak boleh kosong")
    public Catering cateringID;
    @NotNull (message = "Latitude tidak boleh kosong")
    @NotEmpty(message = "Latitude tidak boleh kosong")
    @NotBlank(message = "Latitude tidak boleh blank")
    public String latitude;
    @NotNull (message = "Longitude tidak boleh kosong")
    @NotEmpty(message = "Longitude tidak boleh kosong")
    @NotBlank(message = "Longitude tidak boleh blank")
    public String longitude;
    public Countries country;
    @NotNull (message = "Provinsi tidak boleh kosong")
    @NotEmpty(message = "Provinsi tidak boleh kosong")
    @NotBlank(message = "Provinsi tidak boleh blank")
    public String province;
    @NotNull (message = "Kota tidak boleh kosong")
    @NotEmpty(message = "Kota tidak boleh kosong")
    @NotBlank(message = "Kota tidak boleh blank")
    public String city;
    public Integer limit;

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
}
