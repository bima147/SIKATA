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

@Entity
@Table(name = "MstGroupMenu")
public class GroupMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GroupMenuID")
    private Long groupMenuID;

    @Column(name = "GroupMenuName",length = 50,nullable = false)
    private String groupMenuName;

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

    public Long getGroupMenuID() {
        return groupMenuID;
    }

    public void setGroupMenuID(Long idGroupMenu) {
        this.groupMenuID = idGroupMenu;
    }

    public String getGroupMenuName() {
        return groupMenuName;
    }

    public void setGroupMenuName(String namaGroupMenu) {
        this.groupMenuName = namaGroupMenu;
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

    public void setCreatedAt(Date createdDate) {
        this.createdAt = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long modifiedBy) {
        this.updatedBy = modifiedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date modifiedDate) {
        this.updatedAt = modifiedDate;
    }
}
