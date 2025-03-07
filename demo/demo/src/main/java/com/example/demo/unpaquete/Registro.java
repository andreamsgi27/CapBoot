package com.example.demo.unpaquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Registro {
	public Registro(Configuracion config) {
        this.config = config;
    }

    private configuracion config;
    
    @PostConstruct
    private void init(){
        config.init();
    }
}