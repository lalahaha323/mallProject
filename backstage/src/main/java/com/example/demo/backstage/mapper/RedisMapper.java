package com.example.demo.backstage.mapper;

import com.google.inject.internal.cglib.proxy.$ProxyRefDispatcher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lala
 */
@Repository
@Mapper
public class RedisMapper {
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, 3, TimeUnit.MINUTES);
    }
    public String getValue(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    public Set<String> getPatternKey(String pattern) {
        return template.keys(pattern);
    }

    public void putMap(String key, HashMap value) {
        template.opsForHash().putAll(key, value);
    }

    public Long getNumber(String name) {
        return template.opsForHash().size(name);
    }

    public Map getAll(String name) {
        return template.opsForHash().entries(name);
    }

    public String getMapValue(String map_name, String key) {
        return (String)template.opsForHash().get(map_name, key);
    }
}

