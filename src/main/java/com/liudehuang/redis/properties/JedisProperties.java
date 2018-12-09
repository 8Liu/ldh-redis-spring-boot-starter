package com.liudehuang.redis.properties;

import com.liudehuang.redis.client.ClusterRedis;
import com.liudehuang.redis.client.SingleRedis;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liudehuang
 * @date 2018/12/9 11:22
 * redis单机和集群的配置
 */
@ConfigurationProperties(prefix = "ldh.redis")
public class JedisProperties {
    /**
     * 单例redis
     */
    private SingleRedis singleRedis;
    /**
     * 集群redis
     */
    private ClusterRedis clusterRedis;
    /**
     * 是否开启集群配置
     */
    private boolean openCluster = false;

    public SingleRedis getSingleRedis() {
        return singleRedis;
    }

    public void setSingleRedis(SingleRedis singleRedis) {
        this.singleRedis = singleRedis;
    }

    public ClusterRedis getClusterRedis() {
        return clusterRedis;
    }

    public void setClusterRedis(ClusterRedis clusterRedis) {
        this.clusterRedis = clusterRedis;
    }

    public boolean isOpenCluster() {
        return openCluster;
    }

    public void setOpenCluster(boolean openCluster) {
        this.openCluster = openCluster;
    }
}
