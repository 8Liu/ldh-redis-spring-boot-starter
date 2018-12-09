package com.liudehuang.redis.client;

/**
 * @author liudehuang
 * @date 2018/12/9 11:25
 * 单例redis配置
 */
public class SingleRedis {
    /**
     * 主机
     */
    private String host;
    /**
     * 端口号
     */
    private int port;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接超时时间
     */
    private int timeout = 1000;
    /**
     * 使用的数据库
     */
    private int database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
