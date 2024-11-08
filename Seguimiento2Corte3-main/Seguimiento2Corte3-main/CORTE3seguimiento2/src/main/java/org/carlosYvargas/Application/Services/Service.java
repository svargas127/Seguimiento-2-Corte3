package org.carlosYvargas.Application.Services;

import org.carlosYvargas.Interface.ObjectRepository;

import java.util.List;

public class Service<T> {
    private final ObjectRepository<T> repository;


    public Service(ObjectRepository<T> repository) {
        this.repository = repository;
    }

    public void registrarObject(T object){
        repository.safeObject(object);
    }

    public List<T> listar() {
        return repository.findAll();
    }


    public void eliminarObject(int id){
        repository.deleteObject(id);
    }


    public Object obtenerObject(int id){
        return repository.findObject(id);
    }

    public void modificarObject(T object){
        repository.updateObject(object);
    }



}
