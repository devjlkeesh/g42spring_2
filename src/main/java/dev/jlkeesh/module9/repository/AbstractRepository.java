package dev.jlkeesh.module9.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T, ID extends Serializable> {

    private final EntityManager em;
    private final Class<T> entityClass;
    private final JpaEntityInformation<T, ID> entityInformation;

    @SuppressWarnings("unchecked")
    protected AbstractRepository(EntityManager em) {
        this.em = em;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityInformation = (JpaEntityInformation<T, ID>) JpaEntityInformationSupport.getEntityInformation(entityClass, em);
    }


    @Transactional
    public T save(T entity) {
        Assert.notNull(entity, "entity must not be null");
        if (entityInformation.isNew(entity))
            em.persist(entity);
        else
            em.merge(entity);
        return entity;
    }

    public List<T> findAll() {
        return em.createQuery("select t from " + entityClass.getSimpleName() + " t", entityClass).getResultList();
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

}
