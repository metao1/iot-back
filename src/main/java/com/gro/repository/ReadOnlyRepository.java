package com.gro.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

    @Override
    T findById(ID id);

    @Override
    List<T> findAll();
}
