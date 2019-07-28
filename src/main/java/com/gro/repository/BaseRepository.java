package com.gro.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <S extends T> S save(S entity);

    T findById(ID id);

    Iterable<T> findAll();

    void deleteById(ID id);
}
