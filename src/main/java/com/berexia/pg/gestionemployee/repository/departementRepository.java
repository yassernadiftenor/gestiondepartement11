package com.berexia.pg.gestionemployee.repository;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.entity.employe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface departementRepository extends CrudRepository<departement, Long> {
    public List<departement> findAllByNomDepartContaining(String nomDepart);
    @Query(value = "SELECT * FROM EMPLOYE E ,DEPARTEMENT  D WHERE D.ID_DEPART =E.DEPARTEMENT_ID_DEPART  AND E.DEPARTEMENT_ID_DEPART=?1",nativeQuery = true)
    public List<employe> findEmployeByDepartement(Long idDepart);
}
