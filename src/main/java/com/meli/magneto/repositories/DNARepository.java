package com.meli.magneto.repositories;

import com.meli.magneto.model.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNARepository extends JpaRepository<DNA, String> {

    Long countByMutant(Boolean isMutant);

}
