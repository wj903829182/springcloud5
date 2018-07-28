package com.jack.webflux1.handler;

import com.jack.webflux1.dao.CityRepository;
import com.jack.webflux1.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * create by jack 2018/4/21
 * 城市处理器
 *
 *
 * Mono 和 Flux 适用于两个场景，即：
 Mono：实现发布者，并返回 0 或 1 个元素，即单对象。
 Flux：实现发布者，并返回 N 个元素，即 List 列表对象。
 有人会问，这为啥不直接返回对象，比如返回 City/Long/List。
 原因是，直接使用 Flux 和 Mono 是非阻塞写法，相当于回调方式。
 利用函数式可以减少了回调，因此会看不到相关接口。这恰恰是 WebFlux 的好处：集合了非阻塞 + 异步
 */
@Component
public class CityHandler {

    /**
     * 数据操作的dao层的bean
     */
    private final CityRepository cityRepository;

    /**
     * 通过构造器注入初始化属性cityRepository
     * @param cityRepository
     */
    @Autowired
    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * 保存城市数据的处理方法
     * @param city
     * @return
     */
    public Mono<Long> save(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.save(city)));
    }

    /**
     * 通过城市id查询城市的处理方法
     * @param id
     * @return
     */
    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(cityRepository.findCityById(id));
    }

    /**
     * 查询所有城市数据
     * @return
     */
    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityRepository.findAll());
    }

    /**
     * 修改城市数据
     * @param city
     * @return
     */
    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.updateCity(city)));
    }

    /**
     * 根据城市id删除城市数据
     * @param id
     * @return
     */
    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.deleteCity(id)));
    }

}
