package com.berexia.pg.gestionemployee.controller;

import com.berexia.pg.gestionemployee.entity.departement;
import com.berexia.pg.gestionemployee.exception.emptyFields;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class departementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private departement depart = new departement();
    private departement newDepart = new departement();
    @Test
    public void findDepartementById() {
    }

    @Test
    public void getAllDepartement() {
    }

    @Test
    public void create() throws Exception {
        departement dep = new departement();
        dep.setNomDepart("departement test");
        dep.setCapacite(74L);
        ResultActions rs = mockMvc.perform( post("/departements/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isCreated());
        rs.andDo(print());
    }
    @Test
    public void createKoTestNameEmpty() throws Exception {
        departement dep = new departement();
        dep.setNomDepart("");
        dep.setCapacite(56L);
        ResultActions rs = mockMvc.perform( post("/departements/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isBadRequest());
        rs.andDo(print());
    }
    @Test
    public void createKoTestNameExist() throws Exception {
        departement dep = new departement();
        dep.setNomDepart("ma7al");
        dep.setCapacite(56L);
        ResultActions rs = mockMvc.perform( post("/departements/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isBadRequest());
        rs.andDo(print());
    }
    @Test
    public void updateDepartement() throws Exception {
        departement dep = new departement();
        dep.setNomDepart("dep2");
        dep.setIdDepart(10l);
        dep.setCapacite(56L);
        ResultActions rs = mockMvc.perform( put("/departements/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isOk());
        rs.andDo(print());
    }
    @Test
    public void updateDepartementKoNotFound() throws Exception {
        departement dep = new departement();
        dep.setNomDepart("dep2");
        dep.setIdDepart(101l);
        dep.setCapacite(56L);
        ResultActions rs = mockMvc.perform( put("/departements/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isBadRequest());
        rs.andDo(print());
    }
    @Test
    public void updateDepartementKoNameEmpty() throws Exception {
        departement dep = new departement();
        dep.setNomDepart("");
        dep.setIdDepart(10l);
        dep.setCapacite(56L);
        ResultActions rs = mockMvc.perform( put("/departements/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dep)))
                .andExpect(status().isBadRequest());
        rs.andDo(print());
    }
    @Test
    public void deleteDepartement() {
    }
}