package com.example.demo.unpaquete;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

@Primary
@Qualifier("mentira")
public class RepositorioMock implements Repositorio {
    @Override
    public void guardar(){
        System.err.println("Guardando de mentira");
    }
}
