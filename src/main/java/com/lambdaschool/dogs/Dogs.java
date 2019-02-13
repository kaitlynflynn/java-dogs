package com.lambdaschool.dogs;

import javax.persistence.GeneratedValue;

// Dogs must have an auto generated id, breed name, average weight, boolean True/False if dog is ok for apt living.

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
