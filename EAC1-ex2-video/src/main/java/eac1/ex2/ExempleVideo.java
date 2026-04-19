
package eac1.ex2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author professor
 */
public class ExempleVideo {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		File fitxerOrigen = new File("/home/osboxes/NetBeansProjects/EAC1-ex2-video/exemple.xml");
                String telefonAComparar = "666666666";

		try {
			// Es crea el context indicant la classe arrel
			JAXBContext jaxbContext = JAXBContext.newInstance(Persona.class);
			// Es crea un Unmarshaller amb el context de la classe Persona
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// Es fa servir el mètode unmarshal, per a obtenir les dades
			Persona p = (Persona) jaxbUnmarshaller.unmarshal(fitxerOrigen);

			List<String> telfs = (List<String>) p.getTelefons();
                        
                        boolean trobat =false;
                        for (String telf : telfs) {
                            if(telf.equals(telefonAComparar)){
                                trobat=true;
                            }
                        }
                        if(trobat){
                            System.out.println(p.getNom()+" té aquest telèfon");
                        }else{
                            System.out.println(p.getNom()+" no té aquest telèfon");
                        }
                        
		} catch (JAXBException je) {
                        je.printStackTrace();
		}

	}

}