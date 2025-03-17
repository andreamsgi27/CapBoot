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

import java.util.Arrays;
import java.util.Optional;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        category1 = new Category(1, null, "Category 1", null);
        category2 = new Category(2, null, "Category 2", null);
    }

    @Test
    public void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        var result = categoryService.getAll();

        verify(categoryRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(category1));
        assertTrue(result.contains(category2));
    }

    @Test
    public void testGetCategoryById() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));

        var result = categoryService.getOne(1);

        verify(categoryRepository, times(1)).findById(1);
        assertTrue(result.isPresent());
        assertEquals(category1, result.get());
    }

    @Test
    public void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());

        var result = categoryService.getOne(99);

        assertFalse(result.isPresent());
    }

    @Test
    public void testAddCategory_Success() throws DuplicateKeyException, InvalidDataException {
        when(categoryRepository.existsById(category1.getCategoryId())).thenReturn(false);
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category result = categoryService.add(category1);

        verify(categoryRepository, times(1)).existsById(category1.getCategoryId());
        verify(categoryRepository, times(1)).save(category1);
        assertEquals(category1, result);
    }

    @Test
    public void testAddCategory_AlreadyExists() throws DuplicateKeyException, InvalidDataException {
        when(categoryRepository.existsById(category1.getCategoryId())).thenReturn(true);

        assertThrows(DuplicateKeyException.class, () -> categoryService.add(category1));

        verify(categoryRepository, times(1)).existsById(category1.getCategoryId());
    }

    @Test
    public void testAddCategory_NullCategory() {
        assertThrows(InvalidDataException.class, () -> categoryService.add(null));
    }
}
