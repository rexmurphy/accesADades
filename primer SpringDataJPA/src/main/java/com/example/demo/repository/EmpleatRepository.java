/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Empleat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author m6
 */
@Repository
public interface EmpleatRepository extends JpaRepository<Empleat,String>{
    @Query(value="select e from Empleat e where e.nomEmpleat >= :inferior")
    List<Empleat> findByNomEmpleat(@Param("inferior") String minim);
}
