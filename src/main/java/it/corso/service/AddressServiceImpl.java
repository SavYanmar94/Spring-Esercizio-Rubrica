package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.AddressDao;
import it.corso.dto.AddressDto;
import it.corso.helper.ResponseManager;
import it.corso.model.Address;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao; // Iniezione del DAO per gestire gli indirizzi.
	
	@Autowired 
	private ResponseManager responseManager; // Iniezione del gestore delle risposte.
	
	private ModelMapper mapper = new ModelMapper(); // Un oggetto per mappare i dati tra le entità e i DTO.

	@Override
	public ObjectNode addressRegistration(Address address) {
		// Salva un nuovo indirizzo nel database.
		addressDao.save(address);
		return responseManager.getResponse(201, "Indirizzo registrato con successo"); // Restituisce una risposta di successo.
	}

	@Override
	public ObjectNode addressDataUpdate(Address address) {
		// Cerca l'indirizzo esistente per ID.
		Optional<Address> addressOptional = addressDao.findById(address.getId());
		if (!addressOptional.isPresent())
			return responseManager.getResponse(404, "Indirizzo non trovato"); // Restituisce una risposta di errore se l'indirizzo non esiste.
		Address existing = addressOptional.get();
		existing.setStreet(address.getStreet());
		existing.setCivic(address.getCivic());
		
		addressDao.save(existing); // Aggiorna i dati dell'indirizzo.
		return responseManager.getResponse(202, "Dati dell'indirizzo aggiornati con successo"); // Restituisce una risposta di successo.
	}

	@Override
	public ObjectNode addressRemoval(int id) {
		// Cerca l'indirizzo esistente per ID.
		Optional<Address> addressOptional = addressDao.findById(id);
		if (!addressOptional.isPresent())
			return responseManager.getResponse(404, "Indirizzo non trovato"); // Restituisce una risposta di errore se l'indirizzo non esiste.
		Address address = addressOptional.get();
		
		addressDao.delete(address); // Rimuove l'indirizzo.
		return responseManager.getResponse(202, "Indirizzo rimosso con successo"); // Restituisce una risposta di successo.
	}
	

	@Override
	public List<AddressDto> getAddresses() 
	{
		List<AddressDto> addressesDto = new ArrayList<>();
		List<Address> addresses = (List<Address>) addressDao.findAll();
		addresses.forEach(a -> addressesDto.add(mapper.map(a, AddressDto.class))); // Converte i dati degli indirizzi in DTO.
		return addressesDto; // Restituisce una lista di indirizzi come DTO.
	}
}

