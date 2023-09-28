package com.expensetracker.expense.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.expensetracker.expense.model.Category;

//@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	// @Override
	// List<Categories> findAll();

	// Categories save(String category, String createdBy);

	// Categories findById(long id);

	// Category findCreatedBy(String createdBy);

	Optional<Category> getCategoryByCategoryTitle(String categoryTitle);
}
