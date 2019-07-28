package com.gro.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.prepost.PreAuthorize;

import java.io.Serializable;

@NoRepositoryBean
public interface RestrictedWriteRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    <S extends T> S save(S entity);

}
