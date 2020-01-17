package com.berexia.pg.gestionemployee.controller;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.entity.employe;
import com.berexia.pg.gestionemployee.exception.alreadyExistException;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.service.employeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/employees"})
public class employeController {
    @Autowired
    employeService emplService;


    @GetMapping(value = "/Search/{nomEmpl}")
    public ResponseEntity<List<employe>> findAllByNomDepartLike(@PathVariable String nomEmpl) {

        return new ResponseEntity<>(emplService.findAllByNomEmployeLike(nomEmpl), HttpStatus.OK);
    }

    @GetMapping(value = "/{idEmploye}")
    public ResponseEntity<employe> findEmployeById(@PathVariable Long idEmploye) {

        Optional<employe> empl = emplService.findEmplyeById(idEmploye);
        if (!empl.isPresent()) {
            return new ResponseEntity<employe>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<employe>(empl.get(), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<employe>> getAllEmployees(){
        List<employe> employ =emplService.getAllEmployees();
        if(employ.isEmpty()) {
            return new ResponseEntity<List<employe>>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<List<employe>>(emplService.getAllEmployees(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<employe> create(@RequestBody employe empl) throws emptyFields, alreadyExistException {

            emplService.addEmplye(empl);
        return new ResponseEntity<employe>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<employe> updateEmploye(@RequestBody employe employe) throws emptyFields, notFoundException {
        Optional<employe> empl = emplService.findEmplyeById(employe.getIdEmploye());
        if (!empl.isPresent()) {
            return new ResponseEntity<employe>(employe,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<employe>(emplService.updateEmploye(employe), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) throws notFoundException {
        Optional<employe> dep = emplService.findEmplyeById(id);
        if (!dep.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        emplService.removeEmployeById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
