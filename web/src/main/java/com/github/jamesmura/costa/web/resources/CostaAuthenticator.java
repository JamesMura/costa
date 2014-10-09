package com.github.jamesmura.costa.web.resources;

import com.github.jamesmura.costa.web.models.User;
import com.github.jamesmura.costa.web.persistence.UserDao;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class CostaAuthenticator implements Authenticator<BasicCredentials, User> {
    private final UserDao dao;

    public CostaAuthenticator(UserDao userDao) {
        this.dao = userDao;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        return dao.findUserByUsernameOrEmail(credentials.getUsername());
    }
}
