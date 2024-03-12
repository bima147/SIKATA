package coid.bcafinance.bpsringbootfinal.model;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:23
@Last Modified 05/03/2024 10:23
Version 1.0
*/

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstMenu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuID")
    private Long menuID;

    @Column(name = "Name",nullable = false,length = 30)
    private String name;

    @Column(name = "Host" , nullable = false, length = 30)
    private String host;

    @Column(name = "PathMenu", nullable = false,length = 50)
    private String pathMenu;

    @ManyToOne
    @JoinColumn(name = "GroupMenuID",
            foreignKey = @ForeignKey(name = "FK_TO_GROUP_MENU"))
    private GroupMenu groupMenu;

    @ManyToMany(mappedBy = "ltMenu")
    private List<Access> ltAccess;

    /**
     Start Group Audit trails
     */
    @Column(name = "CreatedBy", nullable = false)
    private Long createdBy = 1L;

    @Column(name = "CreatedAt", columnDefinition = "DATE default GETDATE()")
    private Date createdAt;

    @Column(name = "UpdatedBy")
    private Long updatedBy;
    @Column(name = "UpdatedAt")
    private Date updatedAt;
    /**
     End Group Audit trails
     */

    public Long getMenuID() {
        return menuID;
    }

    public void setMenuID(Long menuID) {
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public GroupMenu getGroupMenu() {
        return groupMenu;
    }

    public void setGroupMenu(GroupMenu groupMenu) {
        this.groupMenu = groupMenu;
    }

    public List<Access> getLtAccess() {
        return ltAccess;
    }

    public void setLtAccess(List<Access> ltAccess) {
        this.ltAccess = ltAccess;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
