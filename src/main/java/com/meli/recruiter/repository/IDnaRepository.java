package com.meli.recruiter.repository;

import com.meli.recruiter.domain.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDnaRepository extends JpaRepository<Dna, Integer> {

    @Query("select count(d.isHuman) as count_mutant_dna from Dna d  where d.isHuman = true")
    Integer humans();

    @Query("select count(d.isHuman) as count_human_dna from Dna d  where d.isHuman = false")
    Integer mutants();

    @Query("select d.isHuman from Dna d where d.dna = :dna")
    Boolean isHuman(String dna);

    Boolean existsByDna(String dna);

}
