package com.devsuperior.desafiobackend.crudclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.desafiobackend.crudclient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
