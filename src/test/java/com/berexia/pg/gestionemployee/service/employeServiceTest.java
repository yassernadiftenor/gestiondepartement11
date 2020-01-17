package com.berexia.pg.gestionemployee.service;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.entity.employe;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.repository.departementRepository;
import com.berexia.pg.gestionemployee.repository.employeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class employeServiceTest {
    @Autowired(required = true)
    employeService emplService;
    @Autowired(required = true)
    departementService departService;
    public static final Logger logger = LogManager.getLogger(employeServiceTest.class.getName());

    @Test
    public void addEmplye() {
        departement dept = new departement();
        dept.setNomDepart("room100");
        dept.setCapacite(57L);
        departService.addDepartement(dept);
        Optional<departement> checkout = departService.findDepartementById(dept.getIdDepart());
        employe empl =new employe();
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("assad");
        empl.setPrenomEmploye("ikram");
        empl.setVille("bruxle");
        emplService.addEmplye(empl);
        logger.info(empl);
        Optional<employe> checkin =emplService.findEmplyeById(empl.getIdEmploye());
        assertEquals(empl.getNomEmploye(), checkin.get().getNomEmploye());
    }
    @Test(expected = emptyFields.class)
    public void addEmplyeKoLastNameEmpty(){
        employe empl =new employe();
        Optional<departement> checkout = departService.findDepartementById(3L);
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("");
        empl.setPrenomEmploye("yassir");
        empl.setVille("casablanca");
        emplService.addEmplye(empl);
    }
    @Test(expected = emptyFields.class)
    public void addEmplyeKoFirstNameEmpty(){
        employe empl =new employe();
        Optional<departement> checkout = departService.findDepartementById(3L);
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("nadif");
        empl.setPrenomEmploye("");
        empl.setVille("casablanca");
        emplService.addEmplye(empl);
    }
    @Test(expected = emptyFields.class)
    public void addEmplyeKoCityEmpty(){
        employe empl =new employe();
        Optional<departement> checkout = departService.findDepartementById(3L);
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("nadif");
        empl.setPrenomEmploye("yassir");
        empl.setVille("");
        emplService.addEmplye(empl);
    }
    @Test(expected = emptyFields.class)
    public void addEmplyeKoDepartementEmpty(){
        employe empl =new employe();
        empl.setDepartement(null);
        empl.setNomEmploye("nadif");
        empl.setPrenomEmploye("yassir");
        empl.setVille("casablanca");
        emplService.addEmplye(empl);
    }
    @Test
    public void findEmplyeById() {
        departement dept = new departement();
        dept.setNomDepart("vamos 41");
        dept.setCapacite(25L);
        departService.addDepartement(dept);
        Optional<departement> checkout = departService.findDepartementById(dept.getIdDepart());
        employe empl =new employe();
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("imad");
        empl.setPrenomEmploye("jaouhari");
        empl.setVille("settat");
        emplService.addEmplye(empl);
        Optional<employe> checkin =emplService.findEmplyeById(empl.getIdEmploye());
        boolean test =checkin.isPresent();
        assertTrue(test);
    }
    @Test
    public void removeEmployeById() {

        departement dept = new departement();
        dept.setNomDepart("departement 212");
        dept.setCapacite(5L);
        departService.addDepartement(dept);
        Optional<departement> checkout = departService.findDepartementById(dept.getIdDepart());
        employe empl =new employe();
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("imran");
        empl.setPrenomEmploye("rami");
        empl.setVille("el jadida");
        emplService.addEmplye(empl);
        logger.info(empl);
        emplService.removeEmployeById(103L);
        Optional<employe> checkin =emplService.findEmplyeById(103L);
        boolean test =checkin.isPresent();
        assertFalse(test);
    }
    @Test(expected = notFoundException.class)
    public void removeEmployeKoTest(){
        emplService.removeEmployeById(45L);
    }
    @Test
    public void updateEmploye() {
        departement dept = new departement();
        dept.setNomDepart("departement support technique");
        dept.setCapacite(660L);
        departService.addDepartement(dept);
        Optional<departement> checkout = departService.findDepartementById(dept.getIdDepart());
        employe empl =new employe();
        empl.setDepartement(checkout.get());
        empl.setNomEmploye("hamza");
        empl.setPrenomEmploye("rabi3i");
        empl.setVille("barcalona");
        emplService.addEmplye(empl);
        logger.info(empl);
        employe empl1 = new employe();
        empl1.setDepartement(empl.getDepartement());
        empl1.setIdEmploye(empl.getIdEmploye());
        empl1.setNomEmploye("imane");
        empl1.setPrenomEmploye("rabi3");
        empl1.setVille(empl.getVille());
        emplService.updateEmploye(empl1);
        Optional<employe> checkin =emplService.findEmplyeById(empl.getIdEmploye());
        assertEquals(checkin.get().getNomEmploye(),empl1.getNomEmploye());
    }
    @Test(expected = notFoundException.class)
    public void updateEmployeKoIdNotFound(){
        departement dept =departService.findDepartementById(6L).get();
        employe empl =new employe();
        empl.setIdEmploye(55L);
        empl.setNomEmploye("hamza");
        empl.setPrenomEmploye("rabi3i");
        empl.setVille("barcalona");
        empl.setDepartement(dept);
        emplService.updateEmploye(empl);
    }
    @Test(expected = emptyFields.class)
    public void updateEmployeKoFirstNameEmpty(){
        departement dept =departService.findDepartementById(6L).get();
        employe empl =new employe();
        empl.setIdEmploye(1L);
        empl.setNomEmploye("");
        empl.setPrenomEmploye("rabi3i");
        empl.setVille("barcalona");
        empl.setDepartement(dept);
        emplService.updateEmploye(empl);
    }
    @Test(expected = emptyFields.class)
    public void updateEmployeKoLastNameEmpty(){
        departement dept =departService.findDepartementById(6L).get();
        employe empl =new employe();
        empl.setIdEmploye(1L);
        empl.setNomEmploye("hamza");
        empl.setPrenomEmploye("");
        empl.setVille("barcalona");
        empl.setDepartement(dept);
        emplService.updateEmploye(empl);
    }
    @Test(expected = emptyFields.class)
    public void updateEmployeKoCityEmpty(){
        departement dept =departService.findDepartementById(6L).get();
        employe empl =new employe();
        empl.setIdEmploye(1L);
        empl.setNomEmploye("hamza");
        empl.setPrenomEmploye("rabi3");
        empl.setVille("");
        empl.setDepartement(dept);
        emplService.updateEmploye(empl);
    }
    @Test
    public void getAllEmployees() {
    }
}