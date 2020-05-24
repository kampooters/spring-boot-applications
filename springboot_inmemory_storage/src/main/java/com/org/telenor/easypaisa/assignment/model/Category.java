package com.org.telenor.easypaisa.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author abdul | 13-Feb-2019 | tim9:40:11 AM
 * @version 1.0
 * Category holds the book category for books
 */
@EntityScan
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
	@GeneratedValue
	private long id;

    @Column(name="name", updatable=false, nullable=false)
    private String name;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}  
}
