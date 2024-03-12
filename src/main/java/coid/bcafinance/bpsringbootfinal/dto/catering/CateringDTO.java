package coid.bcafinance.bpsringbootfinal.dto.catering;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 06/03/2024 12:41
@Last Modified 06/03/2024 12:41
Version 1.0
*/

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CateringDTO {
    private Long cateringID;

    @NotNull (message = "Nama tidak boleh kosong")
    @NotEmpty(message = "Nama tidak boleh kosong")
    @NotBlank(message = "Nama tidak boleh blank")
    private String name;

    private String type;

    public Long getCateringID() {
        return cateringID;
    }

    public void setCateringID(Long cateringID) {
        this.cateringID = cateringID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
