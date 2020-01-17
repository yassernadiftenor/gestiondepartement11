package com.berexia.pg.gestionemployee.service;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.exception.alreadyExistException;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class departementServiceTest {
    @Autowired(required = true)
    departementService derpartementService;
    departement dept1 =new departement();

    public static final Logger logger = LogManager.getLogger(departementServiceTest.class.getName());
    @Test
    public void addDepartement() {
        departement dept = new departement();
        dept.setNomDepart("ma7al");
        dept.setCapacite(50L);
        derpartementService.addDepartement(dept);
        logger.info(dept);
        Optional<departement> checkout = derpartementService.findDepartementById(dept.getIdDepart());
        assertEquals(dept.getNomDepart(), checkout.get().getNomDepart());
    }
    @Test(expected = emptyFields.class)
    public void addDpartementKoNameEmpty(){
        departement dept = new departement();
        dept.setNomDepart("");
        dept.setCapacite(50L);
        derpartementService.addDepartement(dept);
    }
    @Test(expected = alreadyExistException.class)
    public void addDpartementKoNameExist(){
        departement dept = new departement();
        dept.setNomDepart("ma7al");
        dept.setCapacite(50L);
        derpartementService.addDepartement(dept);
    }
    @Test
    public void removeDepartement(){
        departement dept = new departement();
        dept.setNomDepart("ma7al12");
        dept.setCapacite(57L);
        derpartementService.addDepartement(dept);
        logger.info(dept);
        derpartementService.removeDepartementById(dept.getIdDepart());
        Optional<departement> checkout = derpartementService.findDepartementById(dept.getIdDepart());
        boolean test =checkout.isPresent();
        assertFalse(test);
    }
    @Test(expected = notFoundException.class)
    public void removeDepartementNotFoudKo(){
        derpartementService.removeDepartementById(85L);
    }
    @Test
    public void getDepartementById(){
        departement dept = new departement();
        dept.setNomDepart("ma7al412");
        dept.setCapacite(57L);
        derpartementService.addDepartement(dept);
        Optional<departement> checkout = derpartementService.findDepartementById(dept.getIdDepart());
        boolean test =checkout.isPresent();
        assertTrue(test);
    }
    @Test
    public void updateDepartementTest(){
        departement dept = new departement();
        dept.setNomDepart("ma7al411");
        dept.setCapacite(10L);
        derpartementService.addDepartement(dept);
        departement dept1 = new departement();
        dept1.setIdDepart(dept.getIdDepart());
        dept1.setNomDepart("ghogho");
        dept1.setCapacite(41L);
        derpartementService.updateDepartement(dept1);
        Optional<departement> checkout = derpartementService.findDepartementById(dept.getIdDepart());
        assertEquals(dept1.getNomDepart(),checkout.get().getNomDepart());
    }
    @Test(expected = notFoundException.class)
    public void updateDepartementNotFoundTest(){
        departement dept1 = new departement();
        dept1.setIdDepart(20L);
        dept1.setNomDepart("ghogho");
        dept1.setCapacite(41L);
        derpartementService.updateDepartement(dept1);
    }
    @Test(expected = emptyFields.class)
    public void updateDepartementNameEmptyTest(){
        departement dept1 = new departement();
        dept1.setIdDepart(6L);
        dept1.setNomDepart("");
        dept1.setCapacite(41L);
        derpartementService.updateDepartement(dept1);
    }
}
