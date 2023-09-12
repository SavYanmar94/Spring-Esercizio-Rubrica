package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.ContactDto;
import it.corso.model.Contacts;

public interface ContactService {

	// Registra un nuovo contatto e restituisce una risposta.
    ObjectNode contactRegistration(Contacts contact);

    // Aggiorna i dati di un contatto esistente e restituisce una risposta.
    ObjectNode contactDataUpdate(Contacts contact);

    // Rimuove un contatto in base all'ID e restituisce una risposta.
    ObjectNode contactRemoval(int id);

    // Ottiene una lista di contatti e li restituisce come elenco di ContactDto.
    List<ContactDto> getContacts();

    // Ottiene una lista di contatti in base al CAP e li restituisce come elenco di ContactDto.
    List<ContactDto> getContactsbyCAP(String CAP);
}