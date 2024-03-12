package coid.bcafinance.bpsringbootfinal.dto.user.management.menu;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 08/03/2024 10:27
@Last Modified 08/03/2024 10:27
Version 1.0
*/

/**
 * Object untuk Response di class Service
 */
public class MenuResponse3DTO {
    private String pathMenu;
    private String name;
    private String namaGroupMenu;

    public String getPathMenu() {
        return pathMenu;
    }

    public void setPathMenu(String pathMenu) {
        this.pathMenu = pathMenu;
    }

    public String getNamaMenu() {
        return name;
    }

    public void setNamaMenu(String name) {
        this.name = name;
    }

    public String getNamaGroupMenu() {
        return namaGroupMenu;
    }

    public void setNamaGroupMenu(String namaGroupMenu) {
        this.namaGroupMenu = namaGroupMenu;
    }
}