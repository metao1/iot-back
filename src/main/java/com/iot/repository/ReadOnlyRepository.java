package com.iot.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

    @Override
    Optional<T> findById(ID id);

    @Override
    List<T> findAll();
}
