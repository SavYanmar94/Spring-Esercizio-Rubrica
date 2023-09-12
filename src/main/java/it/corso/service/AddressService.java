package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.AddressDto;
import it.corso.model.Address;

public interface AddressService {

    // Registra un nuovo indirizzo e restituisce una risposta.
    ObjectNode addressRegistration(Address address);

    // Aggiorna i dati di un indirizzo esistente e restituisce una risposta.
    ObjectNode addressDataUpdate(Address address);

    // Rimuove un indirizzo in base all'ID e restituisce una risposta.
    ObjectNode addressRemoval(int id);

    // Ottiene una lista di indirizzi e li restituisce come elenco di AddressDto.
    List<AddressDto> getAddresses();
}