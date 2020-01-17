package com.berexia.pg.gestionemployee.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Departement")
public class departement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_depart")
    long idDepart;
    @Column(name ="nomDepart")
    String nomDepart;
    @Column(name = "Capacite")
    long Capacite;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<employe> employes = new ArrayList<employe>();
}
