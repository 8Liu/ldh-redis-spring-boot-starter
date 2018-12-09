package com.liudehuang.redis.client;

import java.util.List;

/**
 * @author liudehuang
 * @date 2018/12/9 14:48
 * 集群redis
 */
public class ClusterRedis {
    /**
     * 密码
     */
    private String password;
    /**
     * 最大连接超时时间
     */
    private int connectionTimeout = 2000;

    private int soTimeout = 2000;

    private int maxAttempts = 5;
    /**
     * 节点
     */
    private List<String> nodes;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
