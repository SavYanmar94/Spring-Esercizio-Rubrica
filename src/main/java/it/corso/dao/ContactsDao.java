package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Contacts;

public interface ContactsDao extends CrudRepository<Contacts, Integer>{

	List<Contacts> findByAddressCap(String CAP);
	
}
