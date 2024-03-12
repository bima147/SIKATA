package coid.bcafinance.bpsringbootfinal.model;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:24
@Last Modified 05/03/2024 10:24
Version 1.0
*/

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstAccess")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccessID")
    private Long accessID;

    @Column(name = "AccessName")
    private String accessName;

    @ManyToMany
    @JoinTable(name = "MapAksesMenu",
            joinColumns = @JoinColumn(name = "AccessID",
                    foreignKey = @ForeignKey(name = "FK_MAP_TO_AKSES")),
            inverseJoinColumns = @JoinColumn(name = "MenuID",
                    foreignKey = @ForeignKey(name = "FK_MAP_TO_MENU"))
    )
    private List<Menu> ltMenu;

    /**
     Start Group Audit trails
     */
    @Column(name = "CreatedBy", nullable = false)
    private Long createdBy = 1L;
    @Column(name = "updatedBy")
    private Long updatedBy;
    @Column(name = "CreatedAt", columnDefinition = "DATE default GETDATE()")
    private Date createdAt;
    @Column(name = "UpdateddAt")
    private Date updatedAt;
    /**
     End Group Audit trails
     */
    public Long getAccessID() {
        return accessID;
    }

    public void setAccessID(Long accessID) {
        this.accessID = accessID;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public List<Menu> getLtMenu() {
        return ltMenu;
    }

    public void setLtMenu(List<Menu> ltMenu) {
        this.ltMenu = ltMenu;
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
