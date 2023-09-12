package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;


import it.corso.dao.ContactsDao;

import it.corso.dto.ContactDto;
import it.corso.helper.ResponseManager;

import it.corso.model.Contacts;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactsDao contactsDao; // Iniezione del DAO per gestire i contatti.
	
	@Autowired 
	private ResponseManager responseManager; // Iniezione del gestore delle risposte.
	
	private ModelMapper mapper = new ModelMapper(); // Un oggetto per mappare i dati tra le entità e i DTO.

	@Override
	public ObjectNode contactRegistration(Contacts contact) {
		// Salva un nuovo contatto nel database.
		contactsDao.save(contact);
		return responseManager.getResponse(201,"Contatto registrato con successo"); // Restituisce una risposta di successo.
	}

	@Override
	public ObjectNode contactDataUpdate(Contacts contact) {
		// Cerca il contatto esistente per ID.
		Optional<Contacts> contactOptional = contactsDao.findById(contact.getId());
		if(!contactOptional.isPresent())
			return responseManager.getResponse(404, "Contatto non trovato"); // Restituisce una risposta di errore se il contatto non esiste.
		Contacts existing = contactOptional.get();
		existing.setName(contact.getName());
		existing.setLastname(contact.getLastname());
		contactsDao.save(existing); // Aggiorna i dati del contatto.
		return responseManager.getResponse(202, "Dati del contatto aggiornati con successo"); // Restituisce una risposta di successo.
	}

	@Override
	public ObjectNode contactRemoval(int id) {
		// Cerca il contatto esistente per ID.
		Optional<Contacts> contactOptional = contactsDao.findById(id);
		if(!contactOptional.isPresent())
			return responseManager.getResponse(404, "Contatto non trovato"); // Restituisce una risposta di errore se il contatto non esiste.
		Contacts contact = contactOptional.get();
		contactsDao.delete(contact); // Rimuove il contatto.
		return responseManager.getResponse(202, "Contatto rimosso con successo"); // Restituisce una risposta di successo.
	}

	@Override
	public List<ContactDto> getContacts() 
	{
		List<ContactDto> contactsDto = new ArrayList<>();
		List<Contacts> contacts = (List<Contacts>) contactsDao.findAll();
		contacts.forEach(a -> contactsDto.add(mapper.map(a, ContactDto.class))); // Converte i dati dei contatti in DTO.
		return contactsDto; // Restituisce una lista di contatti come DTO.
	}

	@Override
	public List<ContactDto> getContactsbyCAP(String CAP) {
		List<ContactDto> contactsDto = new ArrayList<>();
		List<Contacts> contacts = contactsDao.findByAddressCap(CAP);
		contacts.forEach(a -> contactsDto.add(mapper.map(a, ContactDto.class))); // Converte i dati dei contatti in DTO.
		return contactsDto; // Restituisce una lista di contatti filtrata per CAP come DTO.
	}
}
