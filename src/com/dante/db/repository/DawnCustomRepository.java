package com.dante.db.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dante.db.BaseCustomRepository;


/**
 * Contains common methods for every DAO
 * 
 * @author Max
 * 
 * @param <K>
 * @param <E>
 */
public class DawnCustomRepository<E, K extends Serializable> extends BaseCustomRepository<E, K> {
//	protected final Log log = LogFactory.getLog(getClass());

	// Get EntityManagerFactory from DawnPersistenceConfig.java
	// set EntityManager on BaseCustomRepository.java
	@PersistenceContext(unitName="ideaEntityManagerFactory")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}