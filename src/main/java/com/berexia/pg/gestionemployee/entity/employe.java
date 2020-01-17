package com.berexia.pg.gestionemployee.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Employe")
public class employe implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idEmploye")
    long idEmploye;
    @Column(name = "nomEmploye")
    String nomEmploye;
    @Column(name = "prenomEmploye")
    String prenomEmploye;
    @Column(name ="ville")
    String ville;
    @ManyToOne(fetch = FetchType.EAGER)
    departement departement;
}
