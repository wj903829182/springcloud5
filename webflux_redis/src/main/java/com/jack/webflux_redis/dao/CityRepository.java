package com.jack.webflux_redis.dao;

import com.jack.webflux_redis.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * create by jack 2018/5/12
 */
@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Long> {
}
