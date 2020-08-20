package com.masivian.roulettebets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masivian.roulettebets.model.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
	Bet getFindById(Long id);
}
