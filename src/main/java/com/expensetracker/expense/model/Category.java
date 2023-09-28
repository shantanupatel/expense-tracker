package com.expensetracker.expense.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity // it is assumed that this entity is mapped to a table named Categories
@Table(name = "category")
public class Category {

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq")
	@SequenceGenerator(name = "categories_seq", sequenceName = "categories_seq", allocationSize = 1)
	private Long id;
	private String categoryTitle;
	private String createdBy;
	@CreatedDate
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	protected Category() {
		super();
	}

	// public Category(Long id, String categoryTitle, String createdBy) {
	// super();
	// this.id = id;
	// this.categoryTitle = categoryTitle;
	// this.createdBy = createdBy;
	// }

	public Category(String categoryTitle, String createdBy, LocalDateTime createdDate) {
		super();
		this.categoryTitle = categoryTitle;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryTitle=" + categoryTitle + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
