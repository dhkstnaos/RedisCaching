package com.redis.caching;

import lombok.RequiredArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final SearchRepository searchRepository;
    private final RedisTemplate redisTemplate;

    public Person postPerson(Person person) {
        return personRepository.save(person);
    }

    @Cacheable("person")
    public Person getPerson(Long personId) throws Exception {
        return personRepository.findById(personId).orElseThrow(NotFound::new);
    }


    public Search getSearch(Long searchId) throws Exception {
        return searchRepository.findById(searchId).orElseThrow(NotFound::new);
    }


    public Search postKeyword(Long personId,Search search) {
        Search saveSearch = searchRepository.save(search);
        String key = "search::"+personId;
        ListOperations listOperations = redisTemplate.opsForList();
        if(listOperations.size(key)<5) {
            listOperations.rightPush(key,search.getKeyword());
        }
        else if(listOperations.size(key)==5) {
            listOperations.leftPop(key);
            listOperations.rightPush(key,search.getKeyword());
        }
        return saveSearch;
    }

    public List<String> getSearchList(Long personId) {
        String key = "search::"+personId;
        ListOperations listOperations = redisTemplate.opsForList();
        return listOperations.range(key, 0, listOperations.size(key));
    }
}
