package zti.api.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.CategoryRepository;
import zti.api.springboot.jpa.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> getCategories() {
        return repository.findAll();
    }
	
	public Optional<Category> getCategoryById(Long id) {
		if (!repository.existsById(id)) {
		        throw new ResourceNotFoundException("Category with id " + id + " not found");
		}
		return repository.findById(id);
	}

    public Category createCategory(Category category) {
        return repository.save(category);
    }
    
}
