package com.mihailobont.zinkworks.repository;

import com.mihailobont.zinkworks.modal.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
