package com.tma.SpringBootDemo.dto;

import java.util.Date;

/**
 * 
 * @author dangv
 *
 */
public abstract class BaseDTO {

	private Long id;
	private Date createdAt;
	private Date modifiedAt;
	private String createdBy;
	private String modifiedBy;
	private Integer status;

	public BaseDTO() {
		super();
	}

	public BaseDTO(Long id, Date createdAt, Date modifiedAt, Integer status) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
