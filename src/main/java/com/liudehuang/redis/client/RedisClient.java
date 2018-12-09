package com.liudehuang.redis.client;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.MultiKeyCommands;
import redis.clients.jedis.MultiKeyJedisClusterCommands;

/**
 * @author liudehuang
 * @date 2018/12/9 16:03
 */
public abstract class RedisClient implements JedisCommands, MultiKeyCommands, MultiKeyJedisClusterCommands {
}
