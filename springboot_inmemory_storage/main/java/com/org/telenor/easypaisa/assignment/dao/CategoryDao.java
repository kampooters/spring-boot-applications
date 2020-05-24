package com.org.telenor.easypaisa.assignment.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.org.telenor.easypaisa.assignment.model.Category;

/**
 * @author abdul | 13-Feb-2019 | 9:47:10 AM
 * @version 1.0
 * UserDao works as data accessible object for {@link Category}
 */
@RepositoryRestResource
@Transactional
public interface CategoryDao extends CrudRepository<Category, Long> {
	
}