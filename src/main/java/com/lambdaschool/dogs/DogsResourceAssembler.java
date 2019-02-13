package com.lambdaschool.dogs;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DogsResourceAssembler implements ResourceAssembler<Dogs, Resource<Dogs>>
{
    @Override
    public Resource<Dogs> toResource(Dogs dogs)
    {
        return new Resource<>(dogs,
                linkTo(methodOn(DogController.class).findOne(dogs.getId())).withSelfRel(),
                linkTo(methodOn(DogController.class).allDogs()).withRel("dogs"));
    }
}
