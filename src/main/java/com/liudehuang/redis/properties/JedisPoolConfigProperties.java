package com.liudehuang.redis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liudehuang
 * @date 2018/12/9 15:01
 * redis连接池的参数
 */
@ConfigurationProperties(prefix = "ldh.redis.poolConfig")
public class JedisPoolConfigProperties {

    /**
     * 连接池的最大空闲连接，默认是8
     */
    private int maxIdle = 8;
    /**
     * 连接池的最小空闲连接
     */
    private int minIdle;
    /**
     * 最大连接数
     */
    private int maxTotal = 200;
    /**
     *  获取连接的最大等待时间
     */
    private int maxWaitMillis;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，默认为true
     */
    private Boolean testOnBorrow = Boolean.valueOf(true);
    /**
     *申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     */
    private Boolean testWhileIdle = Boolean.valueOf(true);
    /**
     * 1) Destroy线程会检测连接的间隔时间2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private long timeBetweenEvictionRunsMillis = 1000L;
    /**
     * 默认为10
     */
    private int numTestsPerEvictionRun = 10;
    /**
     * 默认为5000
     */
    private int minEvictableIdleTimeMillis = 5000;

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
}
