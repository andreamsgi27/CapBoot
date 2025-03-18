package com.example.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonaItemProcessor implements ItemProcessor<PersonaDTO, Persona> {
private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);

@Override
public Persona process(PersonaDTO item) throws Exception {
    if(item.getId() % 2 == 0 && "Male".equals(item.getFirstName())) return null;

    Persona rslt = new Persona(item.getId(), item.getFirstName() + ", " + item.getLastName(),
    item.getCompany(), item.getJobTitle());

    log.info("Procesando: " + item);
    
    return rslt;
}
}
