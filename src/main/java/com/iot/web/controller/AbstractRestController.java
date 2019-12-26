package com.iot.web.controller;

import com.iot.model.rpicomponent.exception.EntityNotFoundException;
import com.iot.repository.BaseRepository;
import com.iot.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractRestController<T, DTO, ID extends Serializable> {

    protected BaseRepository<T, ID> repository;
    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    public AbstractRestController(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<T> getAll() {
        return this.repository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public T create(@RequestBody DTO obj) {
        logger.debug("create() with request body {} of type {}", obj.toString(), obj.getClass());
        return this.repository.save(getWebUtils().convertToObject(obj));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<T> getById(@PathVariable ID id) {
        Optional<T> t = this.repository.findById(id);
        if (!t.isPresent())
            throw new EntityNotFoundException("Entity with id " + id + " was not found");
        return this.repository.findById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public T updateById(@PathVariable ID id, @RequestBody T obj) {
        logger.debug("update() object with id {} with request body {}", id, obj);
        Optional<T> entity = repository.findById(id);

        try {
            BeanUtils.copyProperties(obj, entity);
        } catch (Exception exception) {
            logger.warn("error while copying properties between beans", exception);
        }

        logger.debug("new merged entity {}", entity);

        return repository.save(entity.get());
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteById(@PathVariable ID id) {
        Optional<T> entity = this.repository.findById(id);
        if (entity.isPresent())
            this.repository.deleteById(id);
        else
            throw new EntityNotFoundException("Entity with id " + id + " was not found");
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    public abstract WebUtils<T> getWebUtils();
}
