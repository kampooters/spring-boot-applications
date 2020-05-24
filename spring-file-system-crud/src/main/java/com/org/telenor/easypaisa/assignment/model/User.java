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
 * User holds the attributes and works as model for sample User
 */

@EntityScan
@Entity
@Table(name = "user_assignment")
public class User {
	@Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
	@GeneratedValue
	private long id;

    @Column(name="name", unique=true, updatable=false, nullable=false)
    private String name;
    
    @Column(name="cnic", unique=false, updatable=true, nullable=false)
    private String cnic;
    
    @Column(name="mobile", unique=false, updatable=true, nullable=false)
    private String mobile;
    
    @Column(name="address", unique=false, updatable=true, nullable=false)
    private String address;

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
	 * @return the cnic
	 */
	public String getCnic() {
		return cnic;
	}

	/**
	 * @param cnic the cnic to set
	 */
	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
    
}
