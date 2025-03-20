package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.Film;
import com.example.demo.entities.Language;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.LanguageRepository;
import com.example.demo.services.services.LanguageServiceImpl;

public class LanguageServiceImplTest {

    @InjectMocks
    private LanguageServiceImpl languageService;

    @Mock
    private LanguageRepository languageRepository;

    //READS
    @Test
    public void testGetAllLanguages() {
        List<Language> languages = List.of(new Language(1, "English"), new Language(2, "Spanish"));
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);
        when(languageRepository.findAll()).thenReturn(languages);

        List<Language> result = languageService.getAll();

        assertEquals(2, result.size());
        assertEquals("English", result.get(0).getName());
        assertEquals("Spanish", result.get(1).getName());
    }

    @Test
    public void testGetLanguageById() {
        Language language = new Language(1, "English");
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);

        when(languageRepository.findById(1)).thenReturn(Optional.of(language));

        Language result = languageService.getOne(1).get();
        assertEquals(1, result.getLanguageId());
        assertEquals("English", result.getName());
    }

    //CREATES
    @Test
    public void testAddLanguage() throws DuplicateKeyException, InvalidDataException {
        Language language = new Language(1, "English");
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);

        when(languageRepository.save(language)).thenReturn(language);
        when(languageRepository.existsById(1)).thenReturn(false);

        Language result = languageService.add(language);

        assertEquals(1, result.getLanguageId());
        assertEquals("English", result.getName());
    }

    @Test
    public void testAddLanguageInvalidDataException() throws DuplicateKeyException, InvalidDataException {
        Language language = null;
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);
        assertThrows(InvalidDataException.class, () -> languageService.add(language));
    }

    @Test
    public void testAddLanguageDuplicateKeyException() throws DuplicateKeyException, InvalidDataException {
        Language language = new Language(1, "English");
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);

        when(languageRepository.existsById(1)).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> languageService.add(language));
    }

    //UPDATES
    @Test
    public void testModifyLanguage() throws InvalidDataException, NotFoundException {
        Language language = new Language(1, "English");
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);

        when(languageRepository.findById(1)).thenReturn(Optional.of(language));
        when(languageRepository.save(language)).thenReturn(language);
        when(languageRepository.existsById(1)).thenReturn(true);
        when(languageRepository.existsById(2)).thenReturn(false);
    }

    @Test
    public void testUpdateLanguageInvalidDataException() throws InvalidDataException {
        Language language = null;
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);
        assertThrows(InvalidDataException.class, () -> languageService.modify(language));
    }

    //DELETES
    @Test
    public void testDeleteLanguage() throws InvalidDataException {
        Language language = new Language(1, "English");
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);

        when(languageRepository.findById(1)).thenReturn(Optional.of(language));
        languageService.delete(language);
        verify(languageRepository, times(1)).delete(language);
    }

    @Test
    public void testDeleteLanguageInvalid() throws InvalidDataException {
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);
        assertThrows(InvalidDataException.class, () -> languageService.delete(null));
    }

    @Test
    public void testDeleteLanguageById() {
        languageRepository = mock(LanguageRepository.class);
        languageService = new LanguageServiceImpl(languageRepository);
        languageService.deleteById(1);
        verify(languageRepository, times(1)).deleteById(1);
    }
}