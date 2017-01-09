package com.dante.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dante.db.entity.CardDetail;

public interface CardDetailRepository extends CardDetailCustomRepository, JpaRepository<CardDetail, Integer>{

	@Query("FROM CardDetail cd WHERE cd.client.id = ?")
	public CardDetail findByClientId(int clientId);

}
