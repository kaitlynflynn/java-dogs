package com.lambdaschool.dogs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration

public class SeedData
{
    @Bean
    public CommandLineRunner initDB(DogsRepository dogsrepo)
    {
        return args -> {
            log.info("Seeding " + dogsrepo.save(new Dogs("Pug", 20, true)));
            log.info("Seeding " + dogsrepo.save(new Dogs("Bulldog", 50, false)));
            log.info("Seeding " + dogsrepo.save(new Dogs("Collie", 50, false)));
            log.info("Seeding " + dogsrepo.save(new Dogs("Boston Terrier", 35, true)));
            log.info("Seeding " + dogsrepo.save(new Dogs("Corgie", 35, true)));
        };
    }
}
