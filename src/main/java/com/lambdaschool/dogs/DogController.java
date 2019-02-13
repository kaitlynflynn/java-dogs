package com.lambdaschool.dogs;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class DogController
{
    private final DogsRepository dogsrepo;
    private final DogsResourceAssembler dogsassembler;

    public DogController(DogsRepository dogsrepo, DogsResourceAssembler dogsassembler)
    {
        this.dogsrepo = dogsrepo;
        this.dogsassembler = dogsassembler;
    }

    // --------------------------------------------------------------------------------------
    @GetMapping("/dogs")
    public Resources<Resource<Dogs>> allDogs()
    {
        List<Resource<Dogs>> dogs = dogsrepo.findAll().stream()
            .map(dogsassembler::toResource)
            .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).allDogs()).withSelfRel());
    }

    // --------------------------------------------------------------------------------------
    @GetMapping("/dogs/{id}")
    public Resource<Dogs> findOne(@PathVariable Long id)
    {
        Dogs foundDog = dogsrepo.findById(id)
                .orElseThrow(() -> new DogNotFoundException(id));

        return dogsassembler.toResource(foundDog);
    }

    // --------------------------------------------------------------------------------------
    @GetMapping("/dogs/breeds")
    public Resources<Resource<Dogs>> sortByBreed()
    {
        List<Resource<Dogs>> dogs = dogsrepo.findAllByOrderByName().stream()
                .map(dogsassembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).allDogs()).withSelfRel());
    }

    // --------------------------------------------------------------------------------------
    @GetMapping("/dogs/weight")
    public Resources<Resource<Dogs>> sortByWeight()
    {
        List<Resource<Dogs>> dogs = dogsrepo.findAllByOrderByWeight().stream()
                .map(dogsassembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).allDogs()).withSelfRel());
    }

    // --------------------------------------------------------------------------------------
    @GetMapping("/dogs/breeds/{breed}")
    public Resource<Dogs> findOneBreed(@PathVariable String breed)
    {
        Dogs foundDog = dogsrepo.findByNameIgnoreCase(breed);

        return dogsassembler.toResource(foundDog);
    }

    // --------------------------------------------------------------------------------------
    @GetMapping("/dogs/apartment")
    public Resources<Resource<Dogs>> getIsApartment()
    {
        List<Resource<Dogs>> dogs = dogsrepo.findByAptIs(true).stream()
                .map(dogsassembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).allDogs()).withSelfRel());
    }

    // --------------------------------------------------------------------------------------
    @PutMapping("/dogs/{id}")
    public ResponseEntity<Dogs> updateDog(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Dogs dogDetails)
    {
        Dogs dogs = dogsrepo.findById(id)
                .orElseThrow(() -> new DogNotFoundException(id));
        dogs.setBreed(dogDetails.getBreed());
        dogs.setWeight(dogDetails.getWeight());
        dogs.setAptOk(dogDetails.isAptOk());
        final Dogs updateDog = dogsrepo.save(dogs);

        return ResponseEntity.ok(updateDog);
    }

    // --------------------------------------------------------------------------------------
    @PostMapping("/dogs")
    public Dogs createDog(@Valid @RequestBody Dogs dog)
    {
        return dogsrepo.save(dog);
    }
}
