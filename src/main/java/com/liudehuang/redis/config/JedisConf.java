package com.liudehuang.redis.config;

import com.liudehuang.redis.properties.JedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liudehuang
 * @date 2018/12/9 15:16
 * 创建redis单例连接和redis集群连接
 */
@Configuration
@EnableConfigurationProperties({JedisProperties.class})
public class JedisConf {
    /**
     * 注入在yaml配置的单机redis或集群redis
     */
    @Autowired
    private JedisProperties jedisProperties;

    /**
     * 注入在yaml配置的连接池相关属性
     */
    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean("jedisPool")
    public JedisPool jedisPool(){
        //如果开启了集群配置，则取消单机配置
        if(this.jedisProperties.isOpenCluster()){
            return null;
        }
        //IP地址
        String host = this.jedisProperties.getSingleRedis().getHost();
        //端口号
        int port = this.jedisProperties.getSingleRedis().getPort();
        //连接超时时间
        int timeout = this.jedisProperties.getSingleRedis().getTimeout();
        //密码
        String password = this.jedisProperties.getSingleRedis().getPassword();
        //用第几个库(默认是第一个库)
        int database = this.jedisProperties.getSingleRedis().getDatabase();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password,database);
        return jedisPool;
    }


    @Bean("jedisCluster")
    public JedisCluster jedisCluster(){
        //如果开启了集群配置，则返回集群主机
        if(jedisProperties.isOpenCluster()){
            Set<HostAndPort> hostAndPortSet = new HashSet<>(16);
            //获取集群配置的节点
            List<String> nodes = this.jedisProperties.getClusterRedis().getNodes();
            if((nodes!=null)&&(nodes.size()>0)){
                for(String hostPort:nodes){
                    //解析出ip和端口号
                    String[] split = hostPort.split(":");
                    HostAndPort hostAndPort = new HostAndPort(split[0],Integer.valueOf(split[1]).intValue());
                    hostAndPortSet.add(hostAndPort);
                }
                int connectionTimeout = this.jedisProperties.getClusterRedis().getConnectionTimeout();
                int soTimeout = this.jedisProperties.getClusterRedis().getSoTimeout();
                int maxAttempts = this.jedisProperties.getClusterRedis().getMaxAttempts();
                String password = this.jedisProperties.getClusterRedis().getPassword();                JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, connectionTimeout, soTimeout, maxAttempts, password, this.jedisPoolConfig);
                return jedisCluster;
            }
        }
        return null;
    }

}
