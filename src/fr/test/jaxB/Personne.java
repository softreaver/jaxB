package fr.test.jaxB;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

 

@XmlRootElement
public class Personne {
private String nom;
private String prenom;
private int taille;
private Date dateNaiss;
 
public Personne() {
}
 
public Personne(String nom, String prenom, int taille, Date dateNaiss) {
super();
this.nom = nom;
this.prenom = prenom;
this.taille = taille;
this.dateNaiss = dateNaiss;
}
 
public Date getDateNaiss() {
return dateNaiss;
}
 
public void setDateNaiss(Date dateNaiss) {
this.dateNaiss = dateNaiss;
}
 
public String getNom() {
return nom;
}
 
public void setNom(String nom) {
this.nom = nom;
}
 
public String getPrenom() {
return prenom;
}
 
public void setPrenom(String prenom) {
this.prenom = prenom;
}
 
public int getTaille() {
return taille;
}
 
public void setTaille(int taille) {
this.taille = taille;
}
}
