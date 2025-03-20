package com.example.demo.events;

public record DomainEvent(String entity, int pk, String property, Object old, Object current) {

}
