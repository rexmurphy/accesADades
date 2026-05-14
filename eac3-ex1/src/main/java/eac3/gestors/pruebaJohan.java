/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Pista;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import net.xqj.basex.BaseXXQDataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 *
 * @author osboxes
 */
public class pruebaJohan {
    private static final String SERVER_NAME="localhost";
    private static final String PORT="1984";
    private static final String USER= "admin";
    private static final String PASSWORD="admin";
    
    private static XQConnection con=null;
    static Pista pista= new Pista("PS-001", "Green Meadow", "Verda", true, 1500, 200, 10, 30, "Polsosa", Arrays.asList("Telecadira Central", "Telesquí Sud"));
    public static final String ARREL = "doc(\"pistes/pistes.xml\")/pistes";
    
    public pruebaJohan(XQConnection con){
        this.con=con;
    }
    
    public static void main(String[] args) throws ManagerException{
        try{
            obrir();
            insert(pista);
        }catch (XQException ex) {
           throw new ManagerException("error", ex);
        }
        
    }
    private static void obrir() throws XQException{
        XQDataSource xqs= new BaseXXQDataSource();
        xqs.setProperty("serverName", SERVER_NAME);
        xqs.setProperty("port", PORT);
        con=xqs.getConnection(USER, PASSWORD);

    }
    
    private static void insert(Pista pista) throws ManagerException {
        //TODO
        try{
           String pistaXml= Utilitats.formaObjecteXML(pista);
        XQExpression query = con.createExpression();
        query.executeQuery(
                "insert node "+pistaXml+" into "+ARREL
        
        ); 
        }catch (XQException ex) {
           throw new ManagerException("error", ex);
        } 
        
       
        
        
        //throw new UnsupportedOperationException("Mètode no implementat");
    }
}


