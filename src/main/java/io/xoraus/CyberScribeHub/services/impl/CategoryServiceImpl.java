package io.xoraus.CyberScribeHub.services.impl;

import io.xoraus.CyberScribeHub.entities.Category;
import io.xoraus.CyberScribeHub.exceptions.ResourceNotFoundException;
import io.xoraus.CyberScribeHub.payloads.CategoryDto;
import io.xoraus.CyberScribeHub.repositories.CategoryRepo;
import io.xoraus.CyberScribeHub.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();

        return categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

}
