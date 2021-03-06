package com.group.integrate.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
 
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
/**
 * Redis缓存配置类
 * @author wangH
 *
 */
@Configuration
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {
 
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
 
    /**
     * @description 自定义的缓存key的生成策略
     *              若想使用这个key  只需要讲注解上keyGenerator的值设置为keyGenerator即可
 
     * @return 自定义策略生成的key
     */
     @Bean
     public KeyGenerator keyGenerator() {
         return new KeyGenerator(){
             @Override
             public Object generate(Object target, Method method, Object... params) {
                 StringBuffer sb = new StringBuffer();
                 sb.append(target.getClass().getName());
                 sb.append(method.getName());
                 for(Object obj:params){
                     sb.append(obj.toString());
                 }
                 return sb.toString();
            }
         };
     }


    //缓存管理器
    @Bean
    public CacheManager cacheManager() {
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
//                .RedisCacheManagerBuilder
//                .fromConnectionFactory(jedisConnectionFactory);
//        Set<String> cacheNames = new HashSet<String>() {{
//            add("codeNameCache");
//            add("codeNameCache1");
//        }};
//        builder.initialCacheNames(cacheNames);
//      return  builder.build();
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30L));
        defaultCacheConfig.disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(jedisConnectionFactory))
                .cacheDefaults(defaultCacheConfig).withInitialCacheConfigurations(Collections.singletonMap
                ("test", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(120L)).disableCachingNullValues())
        ).transactionAware().build();

        return cacheManager;
    }
 
    /**
     * RedisTemplate配置
     *
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory ) {
        //配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);//key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);//value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);//Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
        redisTemplate.afterPropertiesSet();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}