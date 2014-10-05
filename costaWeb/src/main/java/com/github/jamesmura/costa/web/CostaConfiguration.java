package com.github.jamesmura.costa.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

public class CostaConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        String database_url = System.getenv("DATABASE_URL");
        if (database_url == null) {
            return database;
        } else {
            return create(database_url);
        }
    }

    public static DataSourceFactory create(String databaseUrl) {
        if (databaseUrl == null) {
            throw new IllegalArgumentException("The DATABASE_URL environment variable must be set before running the app " +
                    "example: DATABASE_URL=\"postgres://jamesmuranga:jamesmuranga@localhost:5432/costa\"");
        }
        DataSourceFactory datasourceFactory = null;
        try {
            URI dbUri = new URI(databaseUrl);
            String user = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            datasourceFactory = new DataSourceFactory();
            datasourceFactory.setUser(user);
            datasourceFactory.setPassword(password);
            datasourceFactory.setUrl(url);
            datasourceFactory.setDriverClass("org.postgresql.Driver");
        } catch (URISyntaxException e) {
        }
        return datasourceFactory;
    }
}
