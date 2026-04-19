package com.example.demo;

import com.example.demo.dao.EmpleatDao;
import com.example.demo.model.Empleat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 

public class DemoApplication implements CommandLineRunner{

    public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
    }
        
    @Autowired
    private EmpleatDao empleatDao;
    @Override
    public void run(String... args) throws Exception {
        List<Empleat> llista;
        

        
        llista=empleatDao.llistarEmpleats();
        
        System.out.println("Llista de tots els empleats");
        System.out.println("===========================");
        
        llistaEmpleats(llista);
        
        llista=empleatDao.llistarEmpleats("B");
        
        System.out.println("Llistat parcial dels empleats");
        System.out.println("=============================");
        
        llistaEmpleats(llista);
        
    }

    private void llistaEmpleats(List<Empleat> llista) {
        for (Empleat e:llista){
            System.out.println(e.getNomEmpleat());
        }
    }

}
