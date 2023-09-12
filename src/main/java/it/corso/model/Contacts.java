package it.corso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

//Dichiarazione della classe Contacts come entità persistente.
@Entity
@Table(name = "Contacts")
public class Contacts {

 // Identificatore unico generato automaticamente.
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 // Campo "name" contiene solo caratteri alfabetici, spazi, accenti e apostrofi, lunghezza tra 1 e 50.
 @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Errore nel campo nome")
 @Column(name = "name")
 private String name;
 
 // Campo "lastname" contiene solo caratteri alfabetici, spazi, accenti e apostrofi, lunghezza tra 1 e 50.
 @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Errore nel campo cognome")
 @Column(name = "lastname")
 private String lastname;
 
 // Campo "mail" deve seguire il formato di un indirizzo email valido.
 @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Errore nel campo email")
 @Column(name = "mail")
 private String mail;
 
 // Campo "phone" deve seguire il formato di un numero di telefono valido.
 @Pattern(regexp = "^[0-9\\s+.]{10,14}$", message = "Errore nel campo telefono")
 @Column(name = "phone")
 private String phone;
 
 // Associazione con l'entità Address tramite relazione uno a uno.
 @Valid
 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "address_id", referencedColumnName = "id")
 private Address address;

 // Metodi getter e setter per gli attributi della classe.
 


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
