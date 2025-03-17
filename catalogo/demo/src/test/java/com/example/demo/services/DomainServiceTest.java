
package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.core.DomainService;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;


class DomainServiceTest {

	@Mock
	private DomainService<String, Integer> domainService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAll() {
		List<String> mockList = Arrays.asList("Item1", "Item2");
		when(domainService.getAll()).thenReturn(mockList);

		List<String> result = domainService.getAll();

		assertEquals(2, result.size());
		assertEquals("Item1", result.get(0));
		verify(domainService, times(1)).getAll();
	}

	@Test
	void testGetOne() {
		when(domainService.getOne(1)).thenReturn(Optional.of("Item1"));

		Optional<String> result = domainService.getOne(1);

		assertTrue(result.isPresent());
		assertEquals("Item1", result.get());
		verify(domainService, times(1)).getOne(1);
	}

	@Test
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		String newItem = "NewItem";
		when(domainService.add(newItem)).thenReturn(newItem);

		String result = domainService.add(newItem);

		assertEquals(newItem, result);
		verify(domainService, times(1)).add(newItem);
	}

	@Test
	void testAddThrowsDuplicateKeyException() throws DuplicateKeyException, InvalidDataException {
		String newItem = "DuplicateItem";
		when(domainService.add(newItem)).thenThrow(DuplicateKeyException.class);

		assertThrows(DuplicateKeyException.class, () -> domainService.add(newItem));
		verify(domainService, times(1)).add(newItem);
	}

	@Test
	void testModify() throws NotFoundException, InvalidDataException {
		String modifiedItem = "ModifiedItem";
		when(domainService.modify(modifiedItem)).thenReturn(modifiedItem);

		String result = domainService.modify(modifiedItem);

		assertEquals(modifiedItem, result);
		verify(domainService, times(1)).modify(modifiedItem);
	}

	@Test
	void testModifyThrowsNotFoundException() throws NotFoundException, InvalidDataException {
		String modifiedItem = "NonExistentItem";
		when(domainService.modify(modifiedItem)).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class, () -> domainService.modify(modifiedItem));
		verify(domainService, times(1)).modify(modifiedItem);
	}

	@Test
	void testDelete() throws InvalidDataException {
		String itemToDelete = "ItemToDelete";

		doNothing().when(domainService).delete(itemToDelete);

		domainService.delete(itemToDelete);

		verify(domainService, times(1)).delete(itemToDelete);
	}

	@Test
	void testDeleteThrowsInvalidDataException() throws InvalidDataException {
		String itemToDelete = "InvalidItem";
		doThrow(InvalidDataException.class).when(domainService).delete(itemToDelete);

		assertThrows(InvalidDataException.class, () -> domainService.delete(itemToDelete));
		verify(domainService, times(1)).delete(itemToDelete);
	}

	@Test
	void testDeleteById() {
		Integer idToDelete = 1;

		doNothing().when(domainService).deleteById(idToDelete);

		domainService.deleteById(idToDelete);

		verify(domainService, times(1)).deleteById(idToDelete);
	}
}