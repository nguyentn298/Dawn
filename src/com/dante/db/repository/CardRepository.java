package com.dante.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dante.db.entity.Card;

public interface CardRepository extends CardCustomRepository, JpaRepository<Card, Integer>{

}
