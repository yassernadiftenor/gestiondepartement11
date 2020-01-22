package com.berexia.pg.gestionemployee.service;

import com.berexia.pg.gestionemployee.entity.utilisateur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class utilisateurServiceTest {
  @Autowired
  utilisateurService utilisateurService;
    @Test
    public void ajouterUtilisateur() {
        utilisateur user = new utilisateur();
        user.setNom("nadif");
        user.setPrenom("yassir");
        user.setLogin("yasser99");
        user.setPassword("BL154028Y");
        user.setFunction("DEV");
        utilisateur output = utilisateurService.ajouterUtilisateur(user);
        assertEquals(output, user);
    }
}