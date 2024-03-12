package coid.bcafinance.bpsringbootfinal.repo;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.model.Catering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CateringRepo extends JpaRepository<Catering,Long> {
    List<Catering> findByCreatedBy(Long createdBy);
    Page<Catering> findByCateringID(Pageable pageable, Long cateringID);
    Page<Catering> findByNameContainingIgnoreCase(Pageable pageable, String nama);
}
