package com.example.demo.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.services.CategoryServiceImpl;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        category1 = new Category(1, null, "Category 1", null);
        category2 = new Category(2, null, "Category 2", null);
    }

    // READS
    @Test
    public void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

        List<Category> result = categoryService.getAll();

        verify(categoryRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(category1));
        assertTrue(result.contains(category2));
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
    public void testModifyCategory() throws InvalidDataException {
        category1.setName("Updated Category");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category result = categoryService.modify(category1);

        verify(categoryRepository, times(1)).save(category1);
        assertEquals("Updated Category", result.getName());
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
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));

        categoryService.deleteById(1);

        verify(categoryRepository, times(1)).delete(category1);
    }

    @Test
    public void testDeleteCategory_InvalidDataException() throws InvalidDataException {
        Category category = null;
        assertThrows(InvalidDataException.class, () -> categoryService.delete(category));
    }

    @Test
    public void testDeleteByIdCategory_InvalidDataException() throws InvalidDataException {
        Integer categoryId = null;
        assertThrows(InvalidDataException.class, () -> categoryService.deleteById(categoryId));
    }
}