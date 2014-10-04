package com.github.jamesmura.costa.web.persistence;


import com.github.jamesmura.costa.web.models.Cost;

import org.hibernate.SessionFactory;

import java.util.List;

import io.dropwizard.hibernate.AbstractDAO;

public class CostsDao extends AbstractDAO<Cost> {

    public CostsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Cost findById(Long id) {
        return get(id);
    }

    public long create(Cost cost) {
        return persist(cost).getId();
    }

    public List<Cost> findAll() {
        return list(namedQuery("com.github.jamesmura.costa.web.models.Cost.findAll"));
    }
}
