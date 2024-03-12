package coid.bcafinance.bpsringbootfinal.repo;

import coid.bcafinance.bpsringbootfinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:45
@Last Modified 02/03/2024 19:45
Version 1.0
*/

@Transactional
@EnableTransactionManagement
public interface CustomerRepo extends JpaRepository<User, Long> {
}
