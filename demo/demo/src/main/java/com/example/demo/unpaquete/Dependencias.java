package com.example.demo.unpaquete;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Dependencias {
    @Bean
    Repositorio repositorio(Configuration config, Registro registro){
        System.err.println("Soy el bean");
        //return new RepositorioMock();
        return new RepositorioImpl(config, registro);
    }
}
