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

import it.corso.dto.ContactDto;
import it.corso.model.Contacts;
import it.corso.service.ContactService;

@RestController
@RequestMapping("/AddressBook/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
			//endpoint#1:registrazione contacts  localhost:8080/AddressBook/contacts/reg
			@PostMapping("/reg")
			public ResponseEntity<ObjectNode> addressRegistration(@RequestBody Contacts contact){
				ObjectNode response = contactService.contactRegistration(contact);
				return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
			}
			//endpoint#2:modifica dati contact  localhost:8080/AddressBook/contacts/update
			@PutMapping("/update")
			public ResponseEntity<ObjectNode> contactsDataUpdate(@RequestBody Contacts contact){
			ObjectNode response = contactService.contactDataUpdate(contact);
			return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
			}			
			
			//endpoint#3:cancellazione autore  localhost:8080/AddressBook/contacts/delete/{contact_id}
			
			@DeleteMapping("/delete/{id}")
			public ResponseEntity<ObjectNode> contactRemoval(@PathVariable("id")int id){
			ObjectNode response = contactService.contactRemoval(id);
			return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
					}
			//endpoint#4 : elenco contact localhost:8080/AddressBook/contacts/get
			
			@GetMapping("/get")
			public ResponseEntity<List<ContactDto>> getContacts()
			{
			List<ContactDto>response =contactService.getContacts();
			return new ResponseEntity<List<ContactDto>>(response,HttpStatus.OK);
			}
			
			//endpoint#5 : elenco contact localhost:8080/AddressBook/contacts/get/{CAP}
			@GetMapping("/get/{CAP}")
			public ResponseEntity<List<ContactDto>> getContactsbyCAP(@PathVariable("CAP")String CAP)
			{
			List<ContactDto>response =contactService.getContactsbyCAP(CAP);
			return new ResponseEntity<List<ContactDto>>(response,HttpStatus.OK);
			}
			
			
			
			
			
}
