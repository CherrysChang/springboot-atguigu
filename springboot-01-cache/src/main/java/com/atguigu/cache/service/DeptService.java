package com.atguigu.cache.service;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptCacheManager")//因为有多个缓存管理器，需要使用该注解指定具体使用的CacheManager
    @Autowired
    RedisCacheManager deptCacheManager;


    /**
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *  原因是：存的是dept的json数据;我们自定义的CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     *  解决：针对不同的对象，重新注册一个对应的RedisTemplate、CacheManager。或者配置一个通用的Object的RedisTemplate。
     *
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "dept",cacheManager = "deptCacheManager")//指定对应的缓存管理器，或在当前类上使用@CacheConfig指定。如果不指定就会使用默认指定的
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门"+id);
//        Department department = departmentMapper.getDeptById(id);
//        return department;
//    }

    //上面是采用注解的方式，可以采用编码的方式。 使用缓存管理器得到缓存，进行api调用
    public Department getDeptById(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);

        //获取某个缓存（编码形式获取和操作缓存）
        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1",department);//放入缓存

        return department;
    }


}
