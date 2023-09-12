package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.AddressDto;
import it.corso.model.Address;
import it.corso.service.AddressService;

@RestController
@RequestMapping("/AddressBook/Addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	//questi endpoint 1,2,3 non servono poiche facciamo le operazioni direttamente dai contatti , essendo che ogni indirizzo è collegato ad un contatto
	//endpoint#1:registrazione address  localhost:8080/AddressBook/addresses/reg
		@PostMapping("/reg")
		public ResponseEntity<ObjectNode> addressRegistration(@RequestBody Address address){
			ObjectNode response = addressService.addressRegistration(address);
			return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
		}
	
	//endpoint#2:modifica dati address  localhost:8080/AddressBook/addresses/update
		@PutMapping("/update")
		public ResponseEntity<ObjectNode> addressDataUpdate(@RequestBody Address address){
		ObjectNode response = addressService.addressDataUpdate(address);
		return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
		}	
		
		//endpoint#3:cancellazione address  localhost:8080/AddressBook/addresses/delete/{address_id}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<ObjectNode> addressRemoval(@PathVariable("id")int id){
		ObjectNode response = addressService.addressRemoval(id);
		return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
				}
		
		//endpoint#4 : elenco addresses localhost:8080/AddressBook/addresses/get
		
		@GetMapping("/get")
		public ResponseEntity<List<AddressDto>> getAddresses()
		{
		List<AddressDto>response = addressService.getAddresses();
		return new ResponseEntity<List<AddressDto>>(response,HttpStatus.OK);
		}
}
