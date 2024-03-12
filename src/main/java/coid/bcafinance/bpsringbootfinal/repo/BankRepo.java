package coid.bcafinance.bpsringbootfinal.repo;

import coid.bcafinance.bpsringbootfinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@EnableTransactionManagement
public interface BankRepo extends JpaRepository<User, Long> {
}
