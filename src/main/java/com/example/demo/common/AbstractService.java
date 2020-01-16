package com.example.demo.common;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


@Transactional
public abstract class AbstractService<T extends IEntity> implements IService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public AbstractService() {
        super();
    }

    // API

    // find - one

    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id) {
    	Optional<T> entity = getDao().findById(id);
    	return entity.orElse(null);
    }

    // find - all

	@Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

   
    // save/create/persist

    @Override
    public T create(final T entity) {
        Preconditions.checkNotNull(entity);

        final T persistedEntity = getDao().save(entity);

        return persistedEntity;
    }

    // update/merge

    @Override
    public void update(final T entity) {
        Preconditions.checkNotNull(entity);

        getDao().save(entity);
    }

    // delete

    @Override
    public void deleteAll() {
        getDao().deleteAll();
    }

    @Override
    public void delete(final long id) {
        final Optional<T> entity = getDao().findById(id);
        if(entity.isPresent()) {
            getDao().delete(entity.get());  
        } else {
            throw new RuntimeException("Entity not found");
        }
    }

    // count

    @Override
    public long count() {
        return getDao().count();
    }

    // template method

    protected abstract PagingAndSortingRepository<T, Long> getDao();


    // template

   

}
