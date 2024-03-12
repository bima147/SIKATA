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
@Table(name = "MstComponentFood")
public class ComponentFood {
    @Id
    @Column(name = "ComponentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long componentID;
    @NotNull
    @Column(name = "Name")
    public String name;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "MenuID")
    public Food foodID;
    @Column(name = "Measure")
    public String measure;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "CreatedBy")
    public User createdBy;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "UpdatedBy")
    public User updatedBy;
    @Column(name = "CreatedAt", columnDefinition = "Datetime default GETDATE()")
    public Date createdAt;
    @Column(name = "UpdatedAt", columnDefinition = "Datetime")
    public Date updatedAt;

    public Long getComponentID() {
        return componentID;
    }

    public void setComponentID(Long componentID) {
        this.componentID = componentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Food getMenuID() {
        return foodID;
    }

    public void setMenuID(Food foodID) {
        this.foodID = foodID;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
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
