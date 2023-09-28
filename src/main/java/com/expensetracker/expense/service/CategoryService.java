package com.expensetracker.expense.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.expense.model.Category;
import com.expensetracker.expense.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getExpenseCategories() {
		// return List.of(new Category(1L, "Breakfast", "Shantanu Patel"), new
		// Category(2L, "Lunch", "Shantanu Patel"),
		// new Category(3L, "Dinner", "Shantanu Patel"));

		// https://stackoverflow.com/questions/71369047/convert-iterable-to-list-using-streams
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList());

		// repository.save(new Categories("Breakfast", "Shantanu Patel"));
	}

	public void addNewCategory(Category category) {
		// System.out.println(category);
		// TODO Auto-generated method stub
		Optional<Category> categoryByTitle = categoryRepository.getCategoryByCategoryTitle(category.getCategoryTitle());
		//
		// System.out.println(categoryByTitle);
		//
		if (categoryByTitle.isPresent()) {
			throw new IllegalStateException("Category already exists");
		}

		// System.out.println(category);
		categoryRepository.save(category);
	}

	public void deleteCategory(Long categoryId) {
		boolean exists = categoryRepository.existsById(categoryId);

		if (!exists) {
			throw new IllegalStateException("Category with id " + categoryId + " does not exist");
		}

		categoryRepository.deleteById(categoryId);

	}

	@Transactional
	public void updateCategory(Long categoryId, String categoryTitle) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new IllegalStateException("Category with id " + categoryId + " does not exists"));

		if (categoryTitle != null && categoryTitle.length() > 0
				&& !Objects.equals(category.getCategoryTitle(), categoryTitle)) {
			category.setCategoryTitle(categoryTitle);
		}
	}
}
