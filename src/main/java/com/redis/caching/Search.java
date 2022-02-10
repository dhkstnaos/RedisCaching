package com.redis.caching;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search implements Serializable {
    @Id
    @GeneratedValue
    private Long searchId;
    private String keyword;
}
