package com.berexia.pg.gestionemployee.service;


import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.entity.employe;
import com.berexia.pg.gestionemployee.exception.alreadyExistException;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.repository.departementRepository;
import com.berexia.pg.gestionemployee.repository.employeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class departementService {
    @Autowired
    departementRepository departementRepository;
    @Autowired
    employeRepository  employeRepository;
    departement dept;
    public static final Logger logger = LogManager.getLogger(departementService.class.getName());

    public Optional<departement> findDepartementById(Long idDept) {
        return departementRepository.findById(idDept);
    }

    public void addDepartement(departement dept) {
        if (dept.getNomDepart().equals("")) {
            throw new emptyFields("the departement name is empty");
        } else if (departementByName(dept.getNomDepart()) != null) {
            throw new alreadyExistException("the departement name already exist");
        } else {
            departementRepository.save(dept);
            logger.info("departement has been added");
            logger.info(dept);
        }

    }

    public departement departementByName(String deptName) {
        for (departement dept : departementRepository.findAll()) {
            if (dept.getNomDepart().equals(deptName)) {
                return dept;
            }
        }
        return null;
    }

    public void removeDepartementById(Long idDept) {
        if (!findDepartementById(idDept).isPresent()) {
            throw new notFoundException("the following id of departement not found");
        } else {
            logger.info("departement has been removed successfully");
            departementRepository.deleteById(idDept);
        }
    }

    public departement updateDepartement(departement dept) {
        departement deptReturn = null;
        if (dept.getNomDepart() == "" || dept.getNomDepart() == null) {
            throw new emptyFields("the departement name is empty");
        } else if (!findDepartementById(dept.getIdDepart()).isPresent()) {
            throw new notFoundException("the following id of departement not found");
        } else {
            logger.info("departement has been updated successfully");
            deptReturn = departementRepository.save(dept);
        }
        return deptReturn;
    }

    public List<departement> getAllDepartement() {
        return (List<departement>) departementRepository.findAll();
    }

    public  List<departement> findAllByNomDepartLike(String nomDepart){
      return departementRepository.findAllByNomDepartContaining(nomDepart);
    }
    public List<employe> findEmployeByDepartement(Long idDepart){
      List<employe> listEmpl=(List<employe>) employeRepository.findAll();
      List<employe> newList=new ArrayList<>();
        for (employe emp :listEmpl) {
            if(emp.getDepartement().getIdDepart()==idDepart) {
                newList.add(emp);
            }
        }
        return newList;
    }

}
