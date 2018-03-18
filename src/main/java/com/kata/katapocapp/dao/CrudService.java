package com.kata.katapocapp.dao;

import java.io.Serializable;
import java.util.List;

import com.kata.katapocapp.validation.ApplicationException;

/**
 * Created by wassim on 2018/03/18
 */
public interface CrudService<T, ID extends Serializable> {

	/**
	 * Save entity
	 * 
	 * @param entity
	 *            : {@linkplain T} entity domain to persist
	 * @return {@link Boolean}
	 * @throws ApplicationException
	 * @throws Exception
	 */
	public boolean save(T entity); // throws Exception, ApplicationException;

	/**
	 * Load an entity using its Primary Key
	 * 
	 * @param entityId
	 *            : the id of the entity to find
	 * @return entity
	 * @throws ApplicationException
	 * @throws Exception
	 */
	public T findById(ID entityId); // throws Exception, ApplicationException;

	/**
	 * Load all entity.
	 *
	 * @return all entity
	 * @throws ApplicationException
	 * @throws Exception
	 */
	public List<T> findAll(); // throws Exception, ApplicationException;

	/**
	 * Deletes an entity using its Primary Key
	 * 
	 * @param entityId
	 *            : the id of the entity to delete
	 * @return {@link Boolean}
	 * @throws ApplicationException
	 * @throws Exception
	 */
	public boolean delete(ID entityId); // throws Exception,
										// ApplicationException;
}
