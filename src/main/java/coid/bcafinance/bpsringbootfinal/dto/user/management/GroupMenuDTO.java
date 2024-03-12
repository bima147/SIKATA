package coid.bcafinance.bpsringbootfinal.dto.user.management;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 11:10
@Last Modified 05/03/2024 11:10
Version 1.0
*/

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupMenuDTO {
    private Long groupMenuID;

    @NotNull(message = "Field Group Menu Tidak Boleh NULL")
    @NotBlank(message = "Field Group Menu Tidak Boleh Mengandung Spasi")
    @NotEmpty(message = "Field Group Menu Tidak Boleh Kosong")
//    @Pattern(regexp = "^\\w\s{5,50}$",message = "Min 5 Max 50 Hanya Huruf Dan Angka")
    private String groupMenuName;

    public Long getGroupMenuID() {
        return groupMenuID;
    }

    public void setGroupMenuID(Long groupMenuID) {
        this.groupMenuID = groupMenuID;
    }

    public String getGroupMenuName() {
        return groupMenuName;
    }

    public void setGroupMenuName(String groupMenuName) {
        this.groupMenuName = groupMenuName;
    }
}
