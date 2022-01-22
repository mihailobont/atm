package com.mihailobont.zinkworks.repository;

import com.mihailobont.zinkworks.modal.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Atm atm " +
            "SET atm.fiftyEurosBills = :fifty, atm.twentyEurosBills = :twenty, atm.tenEurosBills = :ten, " +
            "atm.fiveEurosBills = :five WHERE atm.id = :atmId")
    int withdraw(@Param("atmId") Long atmId, @Param("fifty") Long fifty, @Param("twenty") Long twenty,
                     @Param("ten") Long ten, @Param("five") Long five);
}
