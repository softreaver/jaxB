package fr.test.jaxB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.helpers.DefaultHandler;

import fr.test.jaxB.Bibliotheque.Livre;

public class JaxB {

	public static void main(String[] args) {
		try {			 
			ObjectFactory objFactory = new ObjectFactory();
			 
			Bibliotheque bibliotheque = (Bibliotheque) objFactory.createBibliotheque();
			List<Livre> livres = bibliotheque.getLivre();
			for (int i = 1; i < 4; i++) {
				Bibliotheque.Livre livreType = objFactory.createBibliothequeLivre();
				Bibliotheque.Livre.Auteur auteur = objFactory.createBibliothequeLivreAuteur();
				auteur.setNom("NOM " + i);
				auteur.setPrenom("Prénom " + i);
				livreType.setAuteur(auteur);
				livreType.setNomEditeur("Editeur" + i);
				livreType.setTitre("Titre" + i);
				livres.add(livreType);
			}
			JAXBContext jaxbContext = JAXBContext.newInstance("fr.test.jaxB");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("resources/schema.xsd"));
			// Schema schema = schemaFactory.newSchema(new File("resources/schema2.xsd"));
			
			Personne personne = new Personne("MILAZZO", "Christopher", 180, new Date());

			marshaller.setSchema(schema);
			marshaller.marshal(bibliotheque, new DefaultHandler());
			// marshaller.marshal(personne, new DefaultHandler());
			
			OutputStream os = new FileOutputStream("resources/data.xml");
			marshaller.marshal(bibliotheque, os);
			
			System.out.println("XML écrit avec succès.");
			
			// ==== unmarshalling ======
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.setSchema(schema);
			
			Bibliotheque biblio = (Bibliotheque) unmarshaller.unmarshal(new File("resources/data.xml"));
			
			String titre = biblio.getLivre().get(0).titre;
			System.out.println("La bibliothèque à été chargée avec succès. Titre du premier livre : " + titre);
			
		} catch (JAXBException je) {
			System.err.println("Erreur lors de la validation du schéma XML !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
