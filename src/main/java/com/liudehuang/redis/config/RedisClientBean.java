package com.liudehuang.redis.config;

import com.liudehuang.redis.client.RedisClient;
import com.liudehuang.redis.client.RedisClientCluster;
import com.liudehuang.redis.client.RedisClientSingle;
import com.liudehuang.redis.properties.JedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author liudehuang
 * @date 2018/12/9 16:49
 */
@Configuration
@EnableConfigurationProperties(JedisProperties.class)
@Component
public class RedisClientBean {
    @Autowired
    private JedisProperties jedisProperties;
    @Resource(name="jedisPool")
    private JedisPool jedisPool;

    @Bean({"redisClient"})
    public RedisClient redisClient() {
        RedisClient redisClient;
        if (this.jedisProperties.isOpenCluster()) {
            redisClient = new RedisClientCluster();
        } else {
            redisClient = new RedisClientSingle(this.jedisPool);
        }
        return redisClient;
    }
}
