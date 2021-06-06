package com.labs.repository;

import com.labs.entities.SoldesBCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldesBCoinRepository extends JpaRepository<SoldesBCoin, Long>{
    Boolean existsByUser_id(String user_id);
    SoldesBCoin getByUser_id(String user_id);

}
