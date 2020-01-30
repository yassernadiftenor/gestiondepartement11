package com.berexia.pg.gestionemployee.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class utilisateur  {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    private String prenom;

    private String function;

    private String login;

    private String password;


}
