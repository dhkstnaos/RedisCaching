package com.redis.caching;

import lombok.RequiredArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public Person postPerson(@RequestPart(value = "person") Person person) {
        return personService.postPerson(person);
    }

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable Long personId) throws Exception {
        return personService.getPerson(personId);
    }

}
