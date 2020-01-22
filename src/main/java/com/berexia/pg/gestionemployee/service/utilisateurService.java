package com.berexia.pg.gestionemployee.service;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.entity.employe;
import com.berexia.pg.gestionemployee.entity.utilisateur;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.berexia.pg.gestionemployee.exception.notFoundException;
import com.berexia.pg.gestionemployee.repository.utilisateurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class utilisateurService {
    public static final Logger log = LogManager.getLogger(utilisateurService.class.getName());

    @Autowired
    utilisateurRepository userRepo;
    public utilisateur ajouterUtilisateur(utilisateur utili) throws emptyFields {
        if(utili.getNom()==null &&utili.getNom()=="") {
            throw new emptyFields("le nom est null !!!!!");
        }else
        if(utili.getPrenom()==null && utili.getPrenom()=="") {
            throw new emptyFields("le prenom est null !!!!!");
        }else
        if(utili.getLogin()==null && utili.getLogin()=="") {
            throw new emptyFields("le login est null !!!!!");
        }else
        if(utili.getPassword()==null && utili.getPassword()=="") {
            throw new emptyFields("le mots de pass est null !!!!!");
        }else
        if(utili.getFunction()==null && utili.getFunction()=="") {
            throw new emptyFields("function est null !!!!!");
        }else {
            log.info("utilisateur bien ajouter");
            return userRepo.save(utili);
        }

    }
    public void modifierUtilisateur(utilisateur utili) throws notFoundException{
        if(utili!=null) {
            Optional<utilisateur> checkUser = userRepo.findById(utili.getId());
            if(checkUser.isPresent()) {
                userRepo.save(utili);
                log.info("utilisateur bien Modifier");
            }else {
                throw new notFoundException("l'id de utilisateur est incorrect");
            }
        }else {
            throw new emptyFields("les donnes est null");
        }

    }
    public void supprimerUtilisateurById(Long id) throws notFoundException{
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            log.info("utilisateur bien Supprimer");

        }else {
            throw new notFoundException("les donnes est incorrect");
        }

    }
    public Optional<utilisateur>  findUtilisateurById(Long id){

        return userRepo.findById(id);
    }
    public List<utilisateur>  findAllUtilisateur(){

        return (List<utilisateur>) userRepo.findAll();
    }
    public boolean findUtlisateurByLoginAndPwd(String login , String pwd) {
        List<utilisateur> listUser  =(List<utilisateur>) userRepo.findAll();
        List<utilisateur> newList=new ArrayList<>();
        for (utilisateur user :listUser) {
            if(user.getLogin().equalsIgnoreCase(login )&& user.getPassword().equals(pwd)) {
               return true;
            }
        }
        return false;
    }
}
