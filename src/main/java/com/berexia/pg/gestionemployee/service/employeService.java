package com.berexia.pg.gestionemployee.service;
import com.berexia.pg.gestionemployee.entity.employe;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.repository.employeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class employeService {
    @Autowired
    employeRepository employeRepository1;
    public static final Logger logger = LogManager.getLogger(employeService.class.getName());


    public employe addEmplye(employe empl){
        if(empl.getNomEmploye().equals("")){
            throw new emptyFields("the last name of employee is empty");
        }else if(empl.getPrenomEmploye().equals("")){
            throw new emptyFields("the first name of emplyee is empty");
        }else if (empl.getVille().equals("")){
            throw new emptyFields("the city of emplyee is empty");
        }else if(Objects.isNull(empl.getDepartement())){
            throw new emptyFields("the Departement of emplyee is empty");
        }
        else {
            logger.info("employee has been added successfully");
            return   employeRepository1.save(empl);
        }
    }
    public Optional<employe> findEmplyeById(long idEmpl){
        return employeRepository1.findById(idEmpl);
    }
    public void removeEmployeById(long idEmpl){
        if(!findEmplyeById(idEmpl).isPresent()){
            throw new notFoundException("the following employee id is not found");

        }else{
            logger.info("employee has been removed successfully");
            employeRepository1.deleteById(idEmpl);
        }
    }
    public employe updateEmploye(employe empl){
        employe emplReturn=null;
        if(empl.getNomEmploye().equals("") || empl.getNomEmploye()==null){
            throw new emptyFields("the new employee's last name is empty ");
        }else if(empl.getPrenomEmploye().equals("") || empl.getPrenomEmploye()==null){
            throw new emptyFields("the new employee's first name is empty ");
        }else if(empl.getVille().equals("") || empl.getVille()==null){
            throw new emptyFields("the city of emplyee is empty");
        }else if(empl.getDepartement()==null){
            throw new emptyFields("the Departement of emplyee is empty");
        } else if (!findEmplyeById(empl.getIdEmploye()).isPresent()) {
            throw new notFoundException("the following employee id is not found");
        }else {
            logger.info("employee has been updated successfully");
            emplReturn= employeRepository1.save(empl);
        }
        return emplReturn;
    }
    public List<employe> getAllEmployees(){
        return (List<employe>) employeRepository1.findAll();
    }

    public  List<employe> findAllByNomEmployeLike(String nomEmpl){
        return employeRepository1.findAllByNomEmployeContaining(nomEmpl);
    }
}
