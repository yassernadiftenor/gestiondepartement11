package com.berexia.pg.gestionemployee.controller;

import com.berexia.pg.gestionemployee.entity.utilisateur;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.service.utilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/utilisateurs"})
public class UtilisaturController {
    @Autowired
    utilisateurService UtilisateurService;
    @GetMapping("/{id}")
    public ResponseEntity<utilisateur> findUtilisateurById(@PathVariable Long id) {
        Optional<utilisateur> utili = UtilisateurService.findUtilisateurById(id);
        if (!utili.isPresent()) {
            return new ResponseEntity<utilisateur>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<utilisateur>(utili.get(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<utilisateur>> getAllUtilisateur() throws emptyFields, notFoundException {
        List<utilisateur> utilis =UtilisateurService.findAllUtilisateur();
        if(utilis.isEmpty()) {
            return new ResponseEntity<List<utilisateur>>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<List<utilisateur>>(UtilisateurService.findAllUtilisateur(),HttpStatus.OK);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<utilisateur> create(@RequestBody utilisateur user) throws emptyFields {
        try {
            UtilisateurService.ajouterUtilisateur(user);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<utilisateur>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<utilisateur>(HttpStatus.CREATED);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateUtilisateur(@RequestBody utilisateur user) throws notFoundException {
        Optional<utilisateur> users = UtilisateurService.findUtilisateurById(user.getId());
        if (!users.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        UtilisateurService.modifierUtilisateur(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) throws notFoundException {
        Optional<utilisateur> dep = UtilisateurService.findUtilisateurById(id);
        if (!dep.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        UtilisateurService.supprimerUtilisateurById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping("/login/{login}/{pwd}")
    public ResponseEntity<Boolean> findUtlisateurByLoginAndPwd(@PathVariable String login,@PathVariable String pwd) {
      try{
            return new ResponseEntity<Boolean>( UtilisateurService.findUtlisateurByLoginAndPwd(login,pwd), HttpStatus.OK);
        }catch (Exception a){
          return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
      }
    }
}
