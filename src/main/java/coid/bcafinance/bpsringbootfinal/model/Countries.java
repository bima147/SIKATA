package coid.bcafinance.bpsringbootfinal.model;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 11/03/2024 12:47
@Last Modified 11/03/2024 12:47
Version 1.0
*/

import javax.persistence.*;

@Entity
@Table(name = "MstCountries")
public class Countries {
    @Id
    @Column(name = "CountryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryID;

    @Column(name = "CountryName", nullable = false)
    private String countryName;

    public Long getCountryID() {
        return countryID;
    }

    public void setCountryID(Long countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
