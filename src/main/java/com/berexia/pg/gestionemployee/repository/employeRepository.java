package com.berexia.pg.gestionemployee.repository;
import com.berexia.pg.gestionemployee.entity.employe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface employeRepository extends CrudRepository<employe,Long> {
    public List<employe> findAllByNomEmployeContaining(String nomEmpl);
}
