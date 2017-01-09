package com.dante.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dante.db.entity.Client;

public interface ClientRepository extends ClientCustomRepository, JpaRepository<Client, Integer>{

}
