package com.redis.caching;

import lombok.RequiredArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    public Person postPerson(Person person) {
        return personRepository.save(person);
    }

    @Cacheable("person")
    public Person getPerson(Long personId) throws Exception {
        return personRepository.findById(personId).orElseThrow(NotFound::new);
    }
}
