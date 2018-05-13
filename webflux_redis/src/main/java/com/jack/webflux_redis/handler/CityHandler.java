package com.jack.webflux_redis.handler;

import com.jack.webflux_redis.dao.CityRepository;
import com.jack.webflux_redis.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * create by jack 2018/5/13
 */
@Component
public class CityHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityHandler.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private final CityRepository cityRepository;

    @Autowired
    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Mono<City> save(City city) {
        return cityRepository.save(city);
    }


    /**
     * 如果缓存存在，从缓存中获取城市信息；
     如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     * @param id
     * @return
     */
    public Mono<City> findCityById(Long id) {

        // 从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityHandler.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return Mono.create(cityMonoSink -> cityMonoSink.success(city));
        }

        // 从 MongoDB 中获取城市信息
        Mono<City> cityMono = cityRepository.findById(id);

        if (cityMono == null)
            return cityMono;

        // 插入缓存
        cityMono.subscribe(cityObj -> {
            operations.set(key, cityObj);
            LOGGER.info("CityHandler.findCityById() : 城市插入缓存 >> " + cityObj.toString());
        });

        return cityMono;
    }

    public Flux<City> findAllCity() {
        return cityRepository.findAll().cache();
    }

    public Mono<City> modifyCity(City city) {

        Mono<City> cityMono = cityRepository.save(city);

        // 缓存存在，删除缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityHandler.modifyCity() : 从缓存中删除城市 ID >> " + city.getId());
        }

        return cityMono;
    }


    /**
     * 如果缓存存在，删除；
     如果缓存不存在，不操作
     * @param id
     * @return
     */
    public Mono<Long> deleteCity(Long id) {

        cityRepository.deleteById(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityHandler.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }

        return Mono.create(cityMonoSink -> cityMonoSink.success(id));
    }
}

