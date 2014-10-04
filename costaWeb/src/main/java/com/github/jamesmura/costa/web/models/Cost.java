package com.github.jamesmura.costa.web.models;

import com.github.jamesmura.costa.models.CostConcept;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "costs")
@NamedQueries({
        @NamedQuery(
                name = "com.github.jamesmura.costa.web.models.Cost.findAll",
                query = "SELECT c FROM Cost c"
        ),
        @NamedQuery(
                name = "com.github.jamesmura.costa.web.models.Cost.findById",
                query = "SELECT c FROM Cost c WHERE c.id = :id"
        )
})
public class Cost extends CostConcept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private Date created = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}
