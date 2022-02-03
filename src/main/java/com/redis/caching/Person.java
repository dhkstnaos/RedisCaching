package com.redis.caching;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Long personId;
    private String name;
    private Integer age;
}