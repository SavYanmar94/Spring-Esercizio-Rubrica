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

//Dichiarazione della classe Address come entità persistente.
@Entity
@Table(name = "Addresses")
public class Address {

 // Identificatore unico generato automaticamente.
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 // Campo "street" contiene solo caratteri alfabetici, spazi, accenti e apostrofi, lunghezza tra 1 e 50.
 @Pattern(regexp = "[a-zA-Z\\sàèìòù'.]{1,50}", message = "Errore nel campo street")
 @Column(name = "street")
 private String street;
 
 // Campo "civic" contiene caratteri alfanumerici, slash, underscore e trattini, lunghezza tra 1 e 5.
 @Pattern(regexp = "[a-zA-Z0-9/_-]{1,5}", message = "Errore nel campo civic")
 @Column(name = "civic")
 private String civic;
 
 // Campo "cap" contiene esattamente 5 cifre.
 @Pattern(regexp = "[0-9]{5}", message = "CAP non valido")
 @Column(name = "cap")
 private String cap;
 
 // Campo "town" contiene solo caratteri alfabetici, spazi, accenti e apostrofi, lunghezza tra 1 e 30.
 @Pattern(regexp = "[a-zA-Z\\sàèìòù'.]{1,30}", message = "Città non valida")
 @Column(name = "town")
 private String town;
 
 // Campo "province" contiene esattamente 2 caratteri alfabetici.
 @Pattern(regexp = "[a-zA-Z]{2}", message = "Provincia non valida")
 @Column(name = "province")
 private String province;
 
 // Metodi getter e setter per gli attributi della classe.
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCivic() {
		return civic;
	}
	public void setCivic(String civic) {
		this.civic = civic;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
}

