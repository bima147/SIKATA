package coid.bcafinance.bpsringbootfinal.dto.user.management.menu;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 08/03/2024 10:26
@Last Modified 08/03/2024 10:26
Version 1.0
*/

/**
 * Object untuk Response di class Service
 */
public class MenuResponseDTO {
    private Long idMenu;
    private String namaMenu;
    private String host;
    private String pathMenu;
    private String namaGroupMenu;

    public String getNamaGroupMenu() {
        return namaGroupMenu;
    }

    public void setNamaGroupMenu(String namaGroupMenu) {
        this.namaGroupMenu = namaGroupMenu;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPathMenu() {
        return pathMenu;
    }

    public void setPathMenu(String pathMenu) {
        this.pathMenu = pathMenu;
    }
}