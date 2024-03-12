package coid.bcafinance.bpsringbootfinal.repo;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:40
@Last Modified 05/03/2024 10:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.model.GroupMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMenuRepo extends JpaRepository<GroupMenu,Long> {
    List<GroupMenu> findTopByGroupMenuName(String name);
    Page<GroupMenu> findByGroupMenuID(Pageable pageable, String id);
    Page<GroupMenu> findByGroupMenuNameContainingIgnoreCase(Pageable pageable, String nama);
}