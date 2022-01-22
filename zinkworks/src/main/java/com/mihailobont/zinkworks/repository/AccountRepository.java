package com.mihailobont.zinkworks.repository;

import com.mihailobont.zinkworks.modal.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean findByAccountNumber(Long accountNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Account a " +
            "SET a.id = :id, a.openingBalance = :openingBalance, a.overdraft = :overdraft WHERE a.id = :id")
    int updateAccountForWithdraw(@Param("id") Long id, @Param("openingBalance") Long openingBalance, @Param("overdraft") Long overdraft);
}
