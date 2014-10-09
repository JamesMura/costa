package com.github.jamesmura.costa.web;

import com.github.jamesmura.costa.web.models.Cost;
import com.github.jamesmura.costa.web.models.User;
import com.github.jamesmura.costa.web.persistence.CostsDao;
import com.github.jamesmura.costa.web.persistence.UserDao;
import com.github.jamesmura.costa.web.resources.CostaAuthenticator;
import com.github.jamesmura.costa.web.resources.CostsResource;
import com.github.jamesmura.costa.web.resources.UserResource;
import de.thomaskrille.dropwizard.environment_configuration.EnvironmentConfigurationFactoryFactory;
import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CostaApplication extends Application<CostaConfiguration> {

    private final HibernateBundle<CostaConfiguration> hibernate = new HibernateBundle<CostaConfiguration>(Cost.class, User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CostaConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new CostaApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CostaConfiguration> bootstrap) {
        bootstrap.setConfigurationFactoryFactory(new EnvironmentConfigurationFactoryFactory());
        bootstrap.addBundle(new MigrationsBundle<CostaConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(CostaConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernate);
    }

    @Override
    public String getName() {
        return "costa";
    }

    @Override
    public void run(CostaConfiguration configuration, Environment environment) throws Exception {
        final CostsDao dao = new CostsDao(hibernate.getSessionFactory());
        final UserDao userDao = new UserDao(hibernate.getSessionFactory());
        environment.jersey().register(new BasicAuthProvider<User>(new CostaAuthenticator(userDao), "costa"));
        environment.jersey().register(new UserResource(userDao));
        environment.jersey().register(new CostsResource(dao));
    }
}
