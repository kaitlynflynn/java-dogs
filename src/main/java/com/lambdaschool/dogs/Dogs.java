package com.lambdaschool.dogs;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Dogs must have an auto generated id, breed name, average weight, boolean True/False if dog is ok for apt living.

@Data
@Entity

public class Dogs
{
    // fields
    private @Id @GeneratedValue Long id;
    private String breed;
    private int weight;
    private boolean aptOk;

    public Dogs()
    {
        // default constructor; needed for JPA
    }

    // constructor
    public Dogs(String breed, int weight, boolean aptOk)
    {
        this.breed = breed;
        this.weight = weight;
        this.aptOk = aptOk;
    }
}
