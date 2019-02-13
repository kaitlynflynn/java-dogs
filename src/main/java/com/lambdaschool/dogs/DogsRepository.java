package com.lambdaschool.dogs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogsRepository extends JpaRepository<Dogs, Long>
{
}
