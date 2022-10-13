package com.tci.tci_assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tci.tci_assignment.entity.Bonus;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long>{

}
