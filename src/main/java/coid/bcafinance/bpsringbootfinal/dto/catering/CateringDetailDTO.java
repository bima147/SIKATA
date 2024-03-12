package coid.bcafinance.bpsringbootfinal.dto.catering;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 11/03/2024 10:04
@Last Modified 11/03/2024 10:04
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.model.Food;
import coid.bcafinance.bpsringbootfinal.model.Location;

import java.util.List;

public class CateringDetailDTO {
    private Long id;
    private String name;
    private String type;
    private List<Location> store;
    private List<Food> food;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Location> getStore() {
        return store;
    }

    public void setStore(List<Location> store) {
        this.store = store;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
