package com.tma.SpringBootDemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * The base entity contains all the same properties and methods
 * 
 * @author dangv
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@CreatedDate
	private Date createdAt;

	@Column
	@LastModifiedDate
	private Date modifiedAt;

	@Column
	@CreatedBy
	private String createdBy;

	@Column
	@LastModifiedBy
	private String modifiedBy;

	@Column
	private Integer status;

	/**
	 * Get created by
	 * 
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Set created by
	 * 
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Get modified by
	 * 
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Set modified by
	 * 
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Get created date
	 * 
	 * @return the created date
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Set created date
	 * 
	 * @param createdAt the created date to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Get modified date
	 * 
	 * @return the modified date to set
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * Set modified date
	 * 
	 * @param modifiedAt the modified date to set
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * Get status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Set status
	 * 
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Get id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}
