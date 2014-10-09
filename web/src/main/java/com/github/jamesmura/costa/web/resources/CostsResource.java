package com.github.jamesmura.costa.web.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.jamesmura.costa.web.models.Cost;
import com.github.jamesmura.costa.web.persistence.CostsDao;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/costs")
@Produces(MediaType.APPLICATION_JSON)
public class CostsResource {
    private final CostsDao dao;

    public CostsResource(CostsDao dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public ArrayList<Cost> getAllCosts() {
        ArrayList<Cost> costs = new ArrayList<Cost>();
        costs.addAll(dao.findAll());
        return costs;
    }

    @POST
    @Timed
    @UnitOfWork
    public Cost add(@Valid Cost cost) {
        long id = dao.create(cost);
        return dao.findById(id);
    }

}
