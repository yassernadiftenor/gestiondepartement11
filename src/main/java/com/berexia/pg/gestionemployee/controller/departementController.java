package com.berexia.pg.gestionemployee.controller;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.entity.employe;
import com.berexia.pg.gestionemployee.exception.alreadyExistException;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.service.departementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/departements"})
public class departementController {
    @Autowired
    departementService departService;

    @GetMapping(value = "/Search/{nomDepart}")
    public ResponseEntity<List<departement>> findAllByNomDepartLike(@PathVariable String nomDepart) {

        return new ResponseEntity<>(departService.findAllByNomDepartLike(nomDepart), HttpStatus.OK);

    }

    @GetMapping(value ="SearchByDepartement/{idDepart}")
    public ResponseEntity<List<employe>> findEmployeByDepartement(@PathVariable Long idDepart){

        return new ResponseEntity<>(departService.findEmployeByDepartement(idDepart),HttpStatus.OK);
    }
    @GetMapping(value = "/{nomDepart}")
    public ResponseEntity<departement> findDepartementByName(@PathVariable String nomDepart) {
        departement dept = departService.departementByName(nomDepart);
        return new ResponseEntity<departement>(dept, HttpStatus.OK);
    }

    @GetMapping(value = "/{idDepart}")
    public ResponseEntity<departement> findDepartementById(@PathVariable Long id) {

        Optional<departement> dep = departService.findDepartementById(id);
        if (!dep.isPresent()) {
            return new ResponseEntity<departement>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<departement>(dep.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<departement>> getAllDepartement() {
        List<departement> depts = departService.getAllDepartement();
        if (depts.isEmpty()) {
            return new ResponseEntity<List<departement>>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<List<departement>>(departService.getAllDepartement(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<departement> create(@RequestBody departement dep) throws emptyFields, alreadyExistException {
        departService.addDepartement(dep);
        return new ResponseEntity<departement>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<departement> updateDepartement(@RequestBody departement dep) throws emptyFields, notFoundException {

        return new ResponseEntity<departement>(departService.updateDepartement(dep), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) throws notFoundException {
        Optional<departement> dep = departService.findDepartementById(id);
        if (!dep.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        departService.removeDepartementById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
