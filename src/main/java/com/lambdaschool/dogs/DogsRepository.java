package com.lambdaschool.dogs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogsRepository extends JpaRepository<Dogs, Long>
{
    List<Dogs> findAllByOrderByName();
    List<Dogs> findAllByOrderByWeight();
    Dogs findByNameIgnoreCase(String breed);
    List<Dogs> findByAptIs(Boolean bool);
}
