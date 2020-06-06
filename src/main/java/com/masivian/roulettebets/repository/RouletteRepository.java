package com.masivian.roulettebets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masivian.roulettebets.model.Roulette;

@Repository
public interface RouletteRepository extends JpaRepository<Roulette, Long> {

}
