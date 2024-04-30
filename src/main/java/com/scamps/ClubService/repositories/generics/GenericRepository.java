package com.scamps.ClubService.repositories.generics;

import com.scamps.ClubService.models.generics.GenericEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T>> extends CrudRepository<T,Long> {

}
