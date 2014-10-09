package com.github.jamesmura.costa.web.persistence;

import com.github.jamesmura.costa.web.models.User;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao extends AbstractDAO<User> {
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(Long id) {
        return get(id);
    }

    public long create(User cost) {
        return persist(cost).getId();
    }

    public List<User> findAll() {
        return list(namedQuery("com.github.jamesmura.costa.web.models.User.findAll"));
    }


    public Optional<User> findUserByUsernameOrEmail(String username) {
        Query query = currentSession().createQuery("from User where username = :text or email = :text ");
        query.setParameter("text", username);
        List list = query.list();
        if (list.size() > 0) {
            return Optional.of((User) list.get(0));
        }
        return Optional.absent();
    }
}