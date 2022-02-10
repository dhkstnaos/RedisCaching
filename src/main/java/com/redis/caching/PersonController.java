package com.redis.caching;

import lombok.RequiredArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
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

    @PostMapping("/{personId}")
    public Search postQueue(
             @PathVariable Long personId
            ,@RequestPart(value = "search") Search search) {
        return personService.postKeyword(personId,search);
    }

    @GetMapping("/search/{search}")
    public Search getSearch(@PathVariable Long searchId) throws Exception {
        return personService.getSearch(searchId);
    }
    @GetMapping("/list/{personId}")
    public List<String> getSearchList(@PathVariable Long personId) throws Exception {
        return personService.getSearchList(personId);
    }
}
