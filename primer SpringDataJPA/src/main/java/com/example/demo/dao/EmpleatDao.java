/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.model.Empleat;
import com.example.demo.repository.EmpleatRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author m6
 */
@Component
public class EmpleatDao {
    @Autowired
    private EmpleatRepository empleatRepository;
    
    public List<Empleat> llistarEmpleats(){
        return empleatRepository.findAll();
    }
    
    public List<Empleat>llistarEmpleats(String minim){
        return empleatRepository.findByNomEmpleat(minim);
    }
    
}
