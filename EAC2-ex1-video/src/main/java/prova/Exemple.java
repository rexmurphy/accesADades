/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Marc
 */
public class Exemple {
    
        
    private static Connection conn = null;
                
    
    private final static String URL="jdbc:postgresql://localhost:5432/test1";
    private final static String USER="ioc";
    private final static String PSW="ioc";
    
    public static void main(String[] args) {
        
        try {
            conn = DriverManager.getConnection(URL,USER,PSW);
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(Exemple.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        colorPreferit("marc");
    }

    private static void colorPreferit(String nom) {
        
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT colorPreferit FROM persona WHERE nom = ?");
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String cp = rs.getString("colorPreferit");
                System.out.println("el color preferit de "+nom+" es "+cp);
            }else{
                System.out.println("no existeix tal nom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exemple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
