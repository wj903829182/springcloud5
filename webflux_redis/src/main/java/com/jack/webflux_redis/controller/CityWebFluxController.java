package com.jack.webflux_redis.controller;

import com.jack.webflux_redis.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * create by jack 2018/5/13
 *
 * 使用 @Autowired 注入 RedisTemplate 对象，这个对象和 Spring 的 JdbcTemplate 功能十分相似，RedisTemplate 封装了 RedisConnection，具有连接管理、序列化和各个操作等，还有针对 String 的支持对象 StringRedisTemplate。
 删除 Redis 某对象，直接通过 key 值调用 delete(key)。
 Redis 操作视图接口类用的是 ValueOperations，对应的是 Redis String/Value 操作，get 是获取数据；set 是插入数据，可以设置失效时间，这里设置的失效时间是 60 s。
 还有其他的操作视图，ListOperations、SetOperations、ZSetOperations 和 HashOperations。
 */
@RestController
@RequestMapping(value = "/city")
public class CityWebFluxController {
    /**
     * RedisTemplate 实现操作 Redis，但操作是同步的，不是 Reactive 的
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        City city = operations.get(key);

        if (!hasKey) {
            return Mono.create(monoSink -> monoSink.success(null));
        }
        return Mono.create(monoSink -> monoSink.success(city));
    }

    @PostMapping()
    public Mono<City> saveCity(@RequestBody City city) {
        String key = "city_" + city.getId();
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        operations.set(key, city, 60, TimeUnit.SECONDS);

        return Mono.create(monoSink -> monoSink.success(city));
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return Mono.create(monoSink -> monoSink.success(id));
    }
}
