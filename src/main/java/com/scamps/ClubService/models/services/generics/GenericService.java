package com.scamps.ClubService.models.services.generics;

import com.scamps.ClubService.models.generics.GenericEntity;
import com.scamps.ClubService.repositories.generics.GenericRepository;

import java.util.List;

public abstract class GenericService<T extends GenericEntity<T>> {

    private final GenericRepository<T> repository;

    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public T getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<T> getAll(){
        return (List<T>) repository.findAll();
    }

    public T create(T object) throws Exception{
        return repository.save(object);
    }

    public T update(T object) throws Exception{
        return repository.save(object);
    }

    public void delete(T object) throws Exception{
        repository.delete(object);
    }
}
