package com.org.telenor.easypaisa.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;
/**
 * @author abdul | 13-Feb-2019 | 9:41:46 AM
 * @version 1.0
 * Book holds the attributes and works as model for sample book
 */
@EntityScan
@Entity
@Table(name = "book")
public class Book {
	
    @Id
    
    @Column(name="ID", unique=true, updatable=false, nullable=false)
	@GeneratedValue
	private long id;

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

	/**
	 * @return the author_name
	 */
	public String getAuthor_name() {
		return author_name;
	}

	/**
	 * @param author_name the author_name to set
	 */
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	/**
	 * @return the category_id
	 */
	public String getCategory_id() {
		return category_id;
	}

	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	@Column(name="name", unique=true, updatable=false, nullable=false)
    private String name;
    
    @Column(name="author_name", unique=false, updatable=true, nullable=false)
    private String author_name; //_ is used to avoid the coulmn name confusion
    
    @Column(name="category_id", unique=false, updatable=true, nullable=false)
    private String category_id;
}
