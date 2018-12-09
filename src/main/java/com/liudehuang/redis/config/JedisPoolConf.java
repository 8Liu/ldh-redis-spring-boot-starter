package com.liudehuang.redis.config;

import com.liudehuang.redis.properties.JedisPoolConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liudehuang
 * @date 2018/12/9 14:55
 *
 * redis连接池的配置
 */
@Configuration
@EnableConfigurationProperties({JedisPoolConfigProperties.class})
public class JedisPoolConf {
    @Autowired
    private JedisPoolConfigProperties jedisPoolConfigProperties;

    @Bean("jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.jedisPoolConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(this.jedisPoolConfigProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(this.jedisPoolConfigProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(this.jedisPoolConfigProperties.getMaxWaitMillis());
        jedisPoolConfig.setMinEvictableIdleTimeMillis(this.jedisPoolConfigProperties.getMinEvictableIdleTimeMillis());
        jedisPoolConfig.setNumTestsPerEvictionRun( this.jedisPoolConfigProperties.getNumTestsPerEvictionRun());
        jedisPoolConfig.setTestOnBorrow(this.jedisPoolConfigProperties.getTestOnBorrow());
        jedisPoolConfig.setTestWhileIdle(this.jedisPoolConfigProperties.getTestWhileIdle());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(this.jedisPoolConfigProperties.getTimeBetweenEvictionRunsMillis());
        return jedisPoolConfig;
    }
}
