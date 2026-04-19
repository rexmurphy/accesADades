/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac4;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import net.xqj.basex.BaseXXQDataSource;


/**
 * Classe que gestiona la persistencia dels objectes de la classe tipus model.Canal
 * @author alumne
 */
public class Exemple {
    
        
    private static final String SERVER_NAME="localhost";
    private static final String USER="admin";
    private static final String PASSWORD="ioc";
    private static final String PORT="1984";
   
    
    
    private static XQConnection con=null;
    
    
    public static void main(String[] args) {
        try {
            obrir();
            obtenirStringXMLPerNom("M6");
            obtenirStringXMLPerNom("M8");
            
            System.out.println("\n"+".".repeat(40)+"\n");
            
            obtenirStringXMLPerTema("Programacio");           
            obtenirStringXMLPerTema("Hobbies");
            
        } catch (XQException ex) {
            Logger.getLogger(Exemple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void obrir() throws XQException{
     XQDataSource xqs = new BaseXXQDataSource();
        xqs.setProperty("serverName", SERVER_NAME);
        xqs.setProperty("port", PORT);
        con = xqs.getConnection(USER, PASSWORD);
     
    }
    
    
    
    public static final String ARREL = "doc(\"canals/canals.xml\")/collection(\"canals\")//"; //arrel del document (permet simplificar les expressions)
    
    
    

    /**
     * Crea un gestor de canal que treballara amb la connexio connex
     * @param con connexio a traves de la qual es fan persistents els canals
     */
    public Exemple(XQConnection con) {
       this.con = con;
    }
    
    
    public static void obtenirStringXMLPerNom(String nom)  {
        try {
            String q=ARREL+"canal[nom=\""+nom+"\"]"; // per exemple: canal[nom="Quatre"]
            XQExpression expr = con.createExpression();
            String  resultat = expr.executeQuery(q).getSequenceAsString(null);
            
            if(resultat.isEmpty()){
                System.out.println("No trobat cap canal anomenat "+nom);
            }else {
                System.out.println("El resultat es:");
                System.out.println(resultat);
            }
        } catch (XQException ex) {
            Logger.getLogger(Exemple.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
    }
    
    public static void obtenirStringXMLPerTema(String tema)  {
        try {
            String q=ARREL+"canal[tema=\""+tema+"\"]"; // per exemple: canal[nom="Quatre"]
            XQExpression expr = con.createExpression();
            String  resultat = expr.executeQuery(q).getSequenceAsString(null);
            
            if(resultat.isEmpty()){
                System.out.println("No trobat cap tema anomenat "+tema);
            }else {
                System.out.println("El resultat es:");
                System.out.println(resultat);
            }
        } catch (XQException ex) {
            Logger.getLogger(Exemple.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
    }

    
}