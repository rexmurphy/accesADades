/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.gestors;

import ex2.model.Estacio;
import ex2.model.Pista;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 *
 * @author joan
 */
public class GestorEstacio {

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer XML
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    
    
    String workspacePath=System.getProperty("user.dir");
    
    public Estacio llegirFitxerXML(String nomFitxer) throws GestorEstacioException {
        
        
        try{
            File origenFileXml = new File(workspacePath, nomFitxer);
        // Create context with the root class
            JAXBContext jaxbContext = JAXBContext.newInstance(Estacio.class);
	// Create Unmarshaller with the context of the root class
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	// Use unmarshal, to obtain the information about the object
            Estacio estacio = (Estacio) jaxbUnmarshaller.unmarshal(origenFileXml);
            return estacio;
        }catch(JAXBException e){
            throw new GestorEstacioException("Error: Cannot read XML file", e);
        }catch(IllegalArgumentException e){
            throw new GestorEstacioException("Error: The file is missing or doesn't exist", e);
        }
    }

    /**
     * Crea un fitxer XML a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut gravar el
     * fitxer
     */
    public void gravarFitxerXML(String nomFitxer, Estacio estacio) throws GestorEstacioException {
        
        
        try{
            
            File DestinationFileXml=new File(workspacePath,nomFitxer);
            // Create context with the root class
            JAXBContext jaxbContext = JAXBContext.newInstance(Estacio.class);
            // Create Marshaller with the context of the root class
            Marshaller jaxbMarshaller= jaxbContext.createMarshaller();
            //Use Marshall
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            jaxbMarshaller.marshal(estacio,DestinationFileXml);
            
            
        }catch(JAXBException e){
            throw new GestorEstacioException("Error creating XML file", e);
        }catch(NullPointerException e){
            throw new GestorEstacioException ("Error: The object cannot be null",e);
        }
    }

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer JSON
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerJSON(String nomFitxer) throws GestorEstacioException {
        File origenFileJson=new  File(workspacePath,nomFitxer);
        try(FileReader reader=new FileReader(origenFileJson)){
            
            Gson gson =new Gson();
            //Take the json file and create an object
            Estacio estacio= gson.fromJson(reader, Estacio.class);
            
            return estacio;
        }catch(Exception e){
            throw new GestorEstacioException("Error: Cannot read JSON file",e);
        }
    }

    /**
     * Crea un fitxer JSON a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut escriure el
     * fitxer
     */
    public void gravarFitxerJSON(String nomFitxer, Estacio estacio) throws GestorEstacioException {
        try {
            if (estacio == null) {
                throw new GestorEstacioException("Error: The object cannot be null");
            }

            if (nomFitxer == null) {
                throw new GestorEstacioException("Error: File name cannot be null");
            }

            String path = (workspacePath != null) ? workspacePath : System.getProperty("user.dir");
            File destinationFileJson = new File(path, nomFitxer);

            if (!destinationFileJson.exists()) {
                throw new GestorEstacioException("Error: The destination file doesn't exist");
            }
            
            try (FileWriter writer = new FileWriter(destinationFileJson)) {
                //Create Json from object and create a file is destiation file exists
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(estacio, writer);
            }

        } catch (JsonIOException | GestorEstacioException | IOException e) {
            
            throw new GestorEstacioException("Error: Cannot write JSON file", e);
            
        }
    }

}
