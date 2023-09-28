package com.expensetracker.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.expense.model.Category;
import com.expensetracker.expense.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
// @RequestMapping(path = "/api/v1/")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoriesService) {
		super();
		this.categoryService = categoriesService;
	}

	@Operation(summary = "Get list of expense categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found expense categories", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Expense categories not found", content = @Content) })
	@GetMapping(path = "/api/v1/categories")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Category> getExpenseCategories() {
		return categoryService.getExpenseCategories();
	}

	@Operation(summary = "Create a new category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created new expense category", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid category name provided", content = @Content),
			@ApiResponse(responseCode = "404", description = "Expense category not found", content = @Content) })
	@PostMapping(path = "/api/v1/categories")
	@ResponseStatus(code = HttpStatus.OK, reason = "Category added successfully")
	public void registerNewCategory(@RequestBody Category category) {
		categoryService.addNewCategory(category);
	}

	// public ResponseEntity<String> registerNewCategory(@RequestBody Category
	// category) {
	// categoryService.addNewCategory(category);
	//
	// return ResponseEntity.status(HttpStatus.OK).body("Category added
	// successfully");
	// }

	@Operation(summary = "Delete an existing category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted expense category", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid category id provided", content = @Content),
			@ApiResponse(responseCode = "404", description = "Expense category not found", content = @Content) })
	@DeleteMapping(path = "/api/v1/categories/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}

	@Operation(summary = "Update an existing category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update expense category", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid category id provided", content = @Content),
			@ApiResponse(responseCode = "404", description = "Expense category not found", content = @Content) })
	@PutMapping(path = "/api/v1/categories/{categoryId}")
	// if a request parameter is to be set as optional make use of
	// @RequestParam(required=false)
	public void updateCategory(@PathVariable("categoryId") Long categoryId,
			@RequestParam String categoryTitle) {
		categoryService.updateCategory(categoryId, categoryTitle);
	}
}
