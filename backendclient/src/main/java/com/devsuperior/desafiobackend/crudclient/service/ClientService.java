package com.devsuperior.desafiobackend.crudclient.service;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafiobackend.crudclient.dto.ClientDTO;
import com.devsuperior.desafiobackend.crudclient.entities.Client;
import com.devsuperior.desafiobackend.crudclient.repositories.ClientRepository;
import com.devsuperior.desafiobackend.crudclient.service.exceptions.DataBaseException;
import com.devsuperior.desafiobackend.crudclient.service.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> listClient = clientRepository.findAll(pageRequest);
		return listClient.map(x -> new ClientDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> objt = clientRepository.findById(id);
		Client clientEntity = objt.orElseThrow(() -> new ResourceNotFoundException("Client Entity not Found"));
		return new ClientDTO(clientEntity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientdto) {
		Client client =  new Client();
		client.setName(clientdto.getName());
		client.setCpf(clientdto.getCpf());
		client.setBirthDate(clientdto.getBirthDate());
		client.setIncome(clientdto.getIncome());
		client.setChildren(clientdto.getChildren());
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientdto) {
		try {

			Client entity = clientRepository.getReferenceById(id); 
			entity.setName(clientdto.getName());
			entity.setCpf(clientdto.getCpf());
			entity.setBirthDate(clientdto.getBirthDate());
			entity.setIncome(clientdto.getIncome());
			entity.setChildren(clientdto.getChildren());
			entity = clientRepository.save(entity);
			return new ClientDTO(entity);

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}


	}

	public void delete(Long id) {
		
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		} catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
		
	}
	
}
