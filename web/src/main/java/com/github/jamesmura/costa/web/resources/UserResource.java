package com.github.jamesmura.costa.web.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.jamesmura.costa.web.models.User;
import com.github.jamesmura.costa.web.persistence.UserDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.HibernateException;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static javax.ws.rs.core.Response.status;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao dao;

    public UserResource(UserDao dao) {
        this.dao = dao;
    }


    @POST
    @Timed
    @UnitOfWork
    public User add(@Valid User user) {
        try {
            long id = dao.create(user);
            return dao.findById(id);
        } catch (final HibernateException exception) {
            return createErrorMessage(exception);
        }
    }

    private User createErrorMessage(final HibernateException exception) {
        HashMap<String, List<String>> errorsHash = new HashMap<String, List<String>>();
        errorsHash.put("errors", Arrays.asList(exception.getMessage()));
        Response.ResponseBuilder b = status(Response.Status.BAD_REQUEST).entity(errorsHash);
        throw new WebApplicationException(b.build());
    }
}
