package com.github.jamesmura.costa.web;

import com.github.jamesmura.costa.web.models.Cost;
import com.github.jamesmura.costa.web.persistence.CostsDao;
import com.github.jamesmura.costa.web.resources.CostsResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CostaApplication extends Application<CostaConfiguration> {

    private final HibernateBundle<CostaConfiguration> hibernate = new HibernateBundle<CostaConfiguration>(Cost.class) {
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
        CostsResource resource = new CostsResource(dao);
        environment.jersey().register(resource);
    }
}
