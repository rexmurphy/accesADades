/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author m6
 */
@Entity
@Table(name="empleat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleat {
    
    @Id
    @Column(name="nomempleat")
    private String nomEmpleat;
    
}
