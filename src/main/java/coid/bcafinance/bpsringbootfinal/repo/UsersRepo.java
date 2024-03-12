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

import coid.bcafinance.bpsringbootfinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


@Transactional
@EnableTransactionManagement
public interface UsersRepo extends JpaRepository<User, Long> {
    /**
     Query untuk proses registrasi
     */
    Optional<User> findTop1ByUsernameOrPhoneOrEmail(String user, String phone, String email);

    /**
     Query untuk proses Login
     Karena hanya login yang sesuai
     */
    Optional<User> findTop1ByUsernameOrPhoneOrEmailAndIsActive(String usr, String noHp, String mail, Boolean isActive);

    /**
     JPQL untuk Query ke Akses
     */
    @Query("SELECT x FROM User x WHERE  x.userID = ?1 AND x.access.accessID = ?2")
    Optional<User> findByUserIDAndAccess(Long userID, Long idAkses);
}
