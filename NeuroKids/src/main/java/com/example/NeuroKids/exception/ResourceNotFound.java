package com.example.NeuroKids.exception;

//resposta http 404
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String recurso, Long id) {
        super(recurso + " com ID " + id + " não encontrado(a).");
    }

    public ResourceNotFound(String mensagem) {
        super(mensagem);
    }
}
