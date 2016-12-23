package com.dante.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.dante.db.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{

}
