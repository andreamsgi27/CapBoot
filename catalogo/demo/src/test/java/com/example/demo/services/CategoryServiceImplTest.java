package com.example.demo.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.services.CategoryServiceImpl;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        category1 = new Category(1, "Category 1");
        category2 = new Category(2, "Category 2");
    }

    // READS
    @Test
    public void testGetAllCategories() {
        List<Category> categories = List.of(category1, category2);
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAll();

        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
    }

    @Test
    public void testGetCategoryById() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));

        Category result = categoryService.getOne(1).get();

        verify(categoryRepository, times(1)).findById(1);
        assertEquals(1, result.getCategoryId());
        assertEquals("Category 1", result.getName());
    }

    // CREATES
    @Test
    public void testAddCategory() throws DuplicateKeyException, InvalidDataException {
        when(categoryRepository.existsById(category1.getCategoryId())).thenReturn(false);
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category result = categoryService.add(category1);

        verify(categoryRepository, times(1)).existsById(category1.getCategoryId());
        verify(categoryRepository, times(1)).save(category1);
        assertEquals(category1, result);
    }

    @Test
    public void testAddCategory_InvalidDataException() throws DuplicateKeyException, InvalidDataException {
        Category category = null;
        assertThrows(InvalidDataException.class, () -> categoryService.add(category));
    }

    @Test
    public void testAddCategory_DuplicateKeyException() throws DuplicateKeyException, InvalidDataException {
        when(categoryRepository.existsById(1)).thenReturn(true);

        assertThrows(DuplicateKeyException.class, () -> categoryService.add(category1));

        verify(categoryRepository, times(1)).existsById(category1.getCategoryId());
    }

    // UPDATES
    @Test
    public void testModifyCategory() throws InvalidDataException, NotFoundException {
        Category category = new Category(1, "Category 1");
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryRepository.existsById(1)).thenReturn(true);
        when(categoryRepository.existsById(2)).thenReturn(false);
    }

    @Test
    public void testModifyCategory_InvalidDataException() throws InvalidDataException {
        Category category = null;
        assertThrows(InvalidDataException.class, () -> categoryService.modify(category));
    }

    // DELETES
    @Test
    public void testDeleteCategory() throws InvalidDataException {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));

        categoryService.delete(category1);

        verify(categoryRepository, times(1)).delete(category1);
    }

    @Test
    public void testDeleteByIdCategory() throws InvalidDataException {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
        categoryService.deleteById(1);
        verify(categoryRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCategory_InvalidDataException() throws InvalidDataException {
        Category category = null;
        assertThrows(InvalidDataException.class, () -> categoryService.delete(category));
    }
}