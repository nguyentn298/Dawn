package com.dante.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface used for every DAO
 * 
 * @author Max
 * 
 * @param <K>
 * @param <E>
 */
public interface IBaseRepository<E, K extends Serializable> {

	public E findFirstRow();

	public E findFirstRow(final String query, final Map params);

	public List<E> findAllItems();
	
	public List<E> findAllWithOrderColumn(String orderColumnName, String orderDirection);

	/**
	 * This method is used to execute native SQL query "SELECT * FROM TABLE_NAME WHERE COLUMN_NAME = ?"
	 * 
	 * @param query
	 * @param values
	 * @return
	 */
	public Object findByNativeQuery(final String query, final Object[] values);

	/**
	 * This method is used to execute Hibernate or JPA query like
	 * "FROM WorkflowNodeMapping wfnm WHERE wfnm.workflowLookup = :workflowLookup"
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public List<E> findByQuery(final String query, final Map params);

	public List findByNamedQuery(final String query, final Map params);

	/**
	 * Execute an update query like "DELETE FROM Employee WHERE employeeId = :employeeId" If there is no parameter, we
	 * can pass NULL for params.
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public int executeUpdateQuery(final String query, final Map params);

	/**
	 * Execute an update query like "DELETE FROM employee WHERE employee_id = :employeeId" If there is no parameter, we
	 * can pass NULL for params.
	 * 
	 * @param nativeQuery
	 * @param params
	 * @return
	 */
	public int executeUpdateNativeQuery(final String nativeQuery, final Map params);

	public <T> T findEntity(final Class<T> entityClass, final Object primaryKey);

	public List findObjectByQuery(final String query, final Map params);
	
	/**
	 * This method execute native SQL query and return unique result.
	 * Sample query: "SELECT * FROM TABLE_NAME WHERE COLUMN_NAME = ?"
	 *  
	 * @param query
	 * @param values
	 * @return
	 */
	public Map findUniqueByNativeQuery(final String query, final Object[] values);
}
