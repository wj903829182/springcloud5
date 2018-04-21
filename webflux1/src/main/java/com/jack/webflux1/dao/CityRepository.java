package com.jack.webflux1.dao;

import com.jack.webflux1.entity.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * create by jack 2018/4/21
 * 数据访问层，负责数据的crud
 */
@Repository
public class CityRepository {
    //保存数据的集合，作为伪数据库
    private ConcurrentMap<Long, City> repository = new ConcurrentHashMap<>();

    //id生成器
    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long save(City city) {
        Long id = idGenerator.incrementAndGet();
        city.setId(id);
        repository.put(id, city);
        return id;
    }

    public Collection<City> findAll() {
        return repository.values();
    }


    public City findCityById(Long id) {
        return repository.get(id);
    }

    public Long updateCity(City city) {
        repository.put(city.getId(), city);
        return city.getId();
    }

    public Long deleteCity(Long id) {
        repository.remove(id);
        return id;
    }
}
