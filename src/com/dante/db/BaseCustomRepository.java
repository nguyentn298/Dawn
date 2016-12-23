package com.dante.db;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains common methods for every DAO
 * 
 * @author Max
 * 
 * @param <K>
 * @param <E>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseCustomRepository<E, K extends Serializable> implements IBaseRepository<E, K> {

	/*	
	*	add <E, K extends Serializable> after BaseCustomRepository to use at child class 
	*	(ex: ProductCustomRepository extends IBaseRepository<Product, Integer>)
	*	Product = E, Integer = K
	*/

	protected final Logger log = LoggerFactory.getLogger(getClass());

	// Declare Generic Object
	// to declare generic <T> object you need to provide to Java Class<T> object,
	// Java will be declared <T> object at runtime by Java Reflection.
	protected Class<E> entityClass;

	protected EntityManager entityManager;

	public abstract void setEntityManager(EntityManager entityManager);

	// Get EntityManager from @PersistenceContext(unitName="ideaEntityManagerFactory")
	// on DawnCustomRepository
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public BaseCustomRepository() {
		// Guest
		// Get type
		Type type = getClass().getGenericSuperclass();
		
		System.out.println("This is generic super class = " + getClass().getGenericSuperclass());
		System.out.println("This is type = " + type);
		System.out.println();
		// check type
		if (type instanceof ParameterizedType) {
			
			ParameterizedType genericSuperclass = (ParameterizedType) type;
			System.out.println("This is ParameterizedType = " + genericSuperclass);
			
			this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
			System.out.println("This is genericSuperclass.getActualTypeArguments()[0] = " + genericSuperclass.getActualTypeArguments()[0]);
			System.out.println("This is entityClass = " + entityClass);
		}
	}

	public E findFirstRow() {
		E obj = null;
		try {
			obj = (E) entityManager.createQuery("from " + entityClass.getName()).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			// empty catch;
		}
		return obj;
	}

	/**
	 * get first result of query
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public E findFirstRow(final String query, final Map params) {
		Query queryObj = entityManager.createQuery(query).setMaxResults(1);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				queryObj.setParameter(paramName, paramValue);
			}
		}
		Object result = null;
		try {
			result = queryObj.getSingleResult();
		} catch (NoResultException e) {
			// empty catch
		}
		return (E) result;
	}

	@Override
	public List<E> findAllItems() {
		Object result = entityManager.createQuery(
				"from " + entityClass.getName()).getResultList();
		return (List<E>) result;
	}

	@Override
	public List<E> findAllWithOrderColumn(final String orderColumnName,
			final String orderDirection) {
		Object result = entityManager.createQuery(
				"SELECT e FROM " + entityClass.getName() + " e ORDER BY e."
						+ orderColumnName + " " + orderDirection)
				.getResultList();
		return (List<E>) result;
	}

	/**
	 * This method is used to execute native SQL query
	 * "SELECT * FROM TABLE_NAME WHERE COLUMN_NAME = ?"
	 * 
	 * @param query
	 * @param values
	 * @return
	 */
	public List findByNativeQuery(final String query, final Object[] values) {
		Query nativeQuery = entityManager.createNativeQuery(query);
		for (int i = 0; i < values.length; i++) {
			nativeQuery.setParameter(i + 1, values[i]);
		}
		Object result = nativeQuery.getResultList();
		return (List) result;
	}

	/**
	 * This method execute native SQL query and return unique result. Sample
	 * query: "SELECT * FROM TABLE_NAME WHERE COLUMN_NAME = ?"
	 * 
	 * @param query
	 * @param values
	 * @return
	 */
	public Map findUniqueByNativeQuery(final String query, final Object[] values) {
		Query nativeQuery = entityManager.createNativeQuery(query);
		for (int i = 0; i < values.length; i++) {
			nativeQuery.setParameter(i + 1, values[i]);
		}
		org.hibernate.transform.ResultTransformer t;
		nativeQuery.unwrap(SQLQuery.class).setResultTransformer(
				AliasToEntityMapResultTransformer.INSTANCE);
		nativeQuery.setMaxResults(1);

		Map result = (Map) nativeQuery.getSingleResult();
		return result;
	}

	/**
	 * This method is used to execute Hibernate or JPA query like
	 * "FROM WorkflowNodeMapping wfnm WHERE wfnm.workflowLookup = :workflowLookup"
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	@Override
	public List<E> findByQuery(final String query, final Map params) {
		Query queryObj = entityManager.createQuery(query);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				queryObj.setParameter(paramName, paramValue);
			}
		}
		Object result = queryObj.getResultList();
		return (List<E>) result;
	}

	/**
	 * get unique result of query
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public E findUniqueResult(final String query, final Map params) {
		Query queryObj = entityManager.createQuery(query);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				queryObj.setParameter(paramName, paramValue);
			}
		}
		Object result = null;
		try {
			result = queryObj.getSingleResult();
		} catch (NoResultException e) {
			// empty catch
		} catch (NonUniqueResultException e) {
			throw new RuntimeException("Non unique result returned", e);
		}
		return (E) result;
	}

	public long getCurrentTimeSQL() {
		final String query = "select SYSDATE()";
		java.sql.Timestamp currentTS = (java.sql.Timestamp) entityManager
				.createNativeQuery(query).getSingleResult();
		return currentTS.getTime();
	}

	/**
	 * Execute an update query like
	 * "DELETE FROM Employee WHERE employeeId = :employeeId" If there is no
	 * parameter, we can pass NULL for params.
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	@Override
	public int executeUpdateQuery(String query, Map params) {
		return executeUpdateQuery(false, query, params);
	}

	/**
	 * Execute an update native query like
	 * "DELETE FROM table_name WHERE column = :employeeId" If there is no
	 * parameter, we can pass NULL for params.
	 * 
	 * @param nativeQuery
	 * @param params
	 * @return
	 */
	@Override
	public int executeUpdateNativeQuery(String nativeQuery, Map params) {
		return executeUpdateQuery(true, nativeQuery, params);
	}

	private int executeUpdateQuery(final boolean isNative, final String query,
			final Map params) {
		Query q = null;
		if (isNative) {
			q = entityManager.createNativeQuery(query);
		} else {
			q = entityManager.createQuery(query);
		}
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				q.setParameter(paramName, paramValue);
			}
		}
		Object result = q.executeUpdate();
		return (Integer) result;
	}

	@Override
	public <T> T findEntity(final Class<T> entityClass, final Object primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	public List findByNamedQuery(final String query, final Map params) {
		Query q = entityManager.createNamedQuery(query);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				q.setParameter(paramName, paramValue);
			}
		}
		List result = q.getResultList();
		return result;
	}

	@Override
	public List findObjectByQuery(final String query, final Map params) {
		Query queryObj = entityManager.createQuery(query);
		if (params != null) {
			Iterator<String> paramNames = params.keySet().iterator();
			while (paramNames.hasNext()) {
				String paramName = paramNames.next();
				Object paramValue = params.get(paramName);
				queryObj.setParameter(paramName, paramValue);
			}
		}
		return queryObj.getResultList();
	}

}