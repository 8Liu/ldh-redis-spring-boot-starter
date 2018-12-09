package com.liudehuang.redis.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.util.*;

/**
 * @author liudehuang
 * @date 2018/12/9 16:23
 */
public class RedisClientCluster extends RedisClient {
    private static final Logger logger = LoggerFactory.getLogger(RedisClientCluster.class);
    private JedisCluster jedisCluster;

    public JedisCluster getJedisCluster() {
        return this.jedisCluster;
    }

    public RedisClientCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public RedisClientCluster() {
    }

    @Override
    public String set(String key, String value) {
        String result = this.jedisCluster.set(key, value);
        return result;
    }

    @Override
    public String get(String key) {
        String result = this.jedisCluster.get(key);
        return result;
    }

    @Override
    public Long del(String key) {
        Long result = this.jedisCluster.del(key);
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Long result = this.jedisCluster.hsetnx(key, field, value);
        return result;
    }

    @Override
    public String hget(String key, String field) {
        String result = this.jedisCluster.hget(key, field);
        return result;
    }

    @Override
    public Long hdel(String key, String... fields) {
        Long result = this.jedisCluster.hdel(key, fields);
        return result;
    }

    @Override
    public Long setnx(String key, String value) {
        Long result = this.jedisCluster.setnx(key, value);
        return result;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Long result = this.jedisCluster.hsetnx(key, field, value);
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Long result = this.jedisCluster.expire(key, seconds);
        return result;
    }

    @Override
    public Long incr(String key) {
        Long result = this.jedisCluster.incr(key);
        return result;
    }

    @Override
    public Long decr(String key) {
        Long result = this.jedisCluster.decr(key);
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Boolean result = this.jedisCluster.exists(key);
        return result;
    }

    @Override
    public Long persist(String key) {
        Long result = this.jedisCluster.persist(key);
        return result;
    }

    @Override
    public Long ttl(String key) {
        Long result = this.jedisCluster.ttl(key);
        return result;
    }

    @Override
    public Long lpush(String key, String... strings) {
        Long result = this.jedisCluster.lpush(key, strings);
        return result;
    }

    @Override
    public String rpop(String key) {
        String result = this.jedisCluster.rpop(key);
        return result;
    }

    @Override
    public Long sadd(String key, String... strings) {
        Long result = this.jedisCluster.sadd(key, strings);
        return result;
    }

    @Override
    public Set<String> smembers(String key) {
        Set<String> result = this.jedisCluster.smembers(key);
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        List<String> result = this.jedisCluster.lrange(key, start, end);
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Map<String, String> result = this.jedisCluster.hgetAll(key);
        return result;
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        Long result = this.jedisCluster.geoadd(key, longitude, latitude, member);
        return result;
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        List<GeoCoordinate> result = this.jedisCluster.geopos(key, members);
        return result;
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        Double result = this.jedisCluster.geodist(key, member1, member2);
        return result;
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        List<GeoRadiusResponse> result = this.jedisCluster.georadiusByMember(key, member, radius, unit);
        return result;
    }

    @Override
    public Set<String> keys(String pattern) {
        Map<String, JedisPool> clusterNodes = this.jedisCluster.getClusterNodes();
        Collection<JedisPool> nodes = clusterNodes.values();
        Iterator<JedisPool> iterator = nodes.iterator();
        Set<String> keys = new HashSet();
        while (iterator.hasNext()) {
            Jedis jedis = null;
            try {
                JedisPool jedisPool = (JedisPool) iterator.next();
                jedis = jedisPool.getResource();
                keys.addAll(jedis.keys(pattern));
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        return keys;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        Set<String> zrange = this.jedisCluster.zrange(key, start, end);
        return zrange;
    }

    @Override
    public Long rpush(String key, String... strings) {
        Long result = this.jedisCluster.rpush(key, strings);
        return result;
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        return this.jedisCluster.set(key, value, nxxx, expx, time);
    }

    @Override
    @Deprecated
    public String set(String key, String value, String nxxx) {
        return this.jedisCluster.set(key, value, nxxx);
    }

    @Override
    public String type(String key) {
        return this.jedisCluster.type(key);
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return this.jedisCluster.pexpire(key, milliseconds);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return this.jedisCluster.expireAt(key, unixTime);
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return this.jedisCluster.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(String key) {
        return this.jedisCluster.pttl(key);
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return this.jedisCluster.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return this.jedisCluster.setbit(key, offset, value);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return this.jedisCluster.getbit(key, offset);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return this.jedisCluster.setrange(key, offset, value);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return this.jedisCluster.getrange(key, startOffset, endOffset);
    }

    @Override
    public String getSet(String key, String value) {
        return this.jedisCluster.getSet(key, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return this.jedisCluster.setex(key, seconds, value);
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return this.jedisCluster.psetex(key, milliseconds, value);
    }

    @Override
    public Long decrBy(String key, long integer) {
        return this.jedisCluster.decrBy(key, integer);
    }

    @Override
    public Long incrBy(String key, long integer) {
        return this.jedisCluster.incrBy(key, integer);
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return this.jedisCluster.incrByFloat(key, value);
    }

    @Override
    public Long append(String key, String value) {
        return this.jedisCluster.append(key, value);
    }

    @Override
    public String substr(String key, int start, int end) {
        return this.jedisCluster.substr(key, start, end);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return this.jedisCluster.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return this.jedisCluster.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return this.jedisCluster.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return this.jedisCluster.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return this.jedisCluster.hexists(key, field);
    }

    @Override
    public Long hlen(String key) {
        return this.jedisCluster.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return this.jedisCluster.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return this.jedisCluster.hvals(key);
    }

    @Override
    public Long llen(String key) {
        return this.jedisCluster.llen(key);
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return this.jedisCluster.ltrim(key, start, end);
    }

    @Override
    public String lindex(String key, long index) {
        return this.jedisCluster.lindex(key, index);
    }

    @Override
    public String lset(String key, long index, String value) {
        return this.jedisCluster.lset(key, index, value);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return this.jedisCluster.lrem(key, count, value);
    }

    @Override
    public String lpop(String key) {
        return this.jedisCluster.lpop(key);
    }

    @Override
    public Long srem(String key, String... member) {
        return this.jedisCluster.srem(key, member);
    }

    @Override
    public String spop(String key) {
        return this.jedisCluster.spop(key);
    }

    @Override
    public Set<String> spop(String key, long count) {
        return this.jedisCluster.spop(key, count);
    }

    @Override
    public Long scard(String key) {
        return this.jedisCluster.scard(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return this.jedisCluster.sismember(key, member);
    }

    @Override
    public String srandmember(String key) {
        return this.jedisCluster.srandmember(key);
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return this.jedisCluster.srandmember(key, count);
    }

    @Override
    public Long strlen(String key) {
        return this.jedisCluster.strlen(key);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return this.jedisCluster.zadd(key, score, member);
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return this.jedisCluster.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return this.jedisCluster.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return this.jedisCluster.zadd(key, scoreMembers, params);
    }

    @Override
    public Long zrem(String key, String... member) {
        return this.jedisCluster.zrem(key, member);
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return this.jedisCluster.zincrby(key, score, member);
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return this.jedisCluster.zincrby(key, score, member, params);
    }

    @Override
    public Long zrank(String key, String member) {
        return this.jedisCluster.zrank(key, member);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return this.jedisCluster.zrevrank(key, member);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return this.jedisCluster.zrevrange(key, start, end);
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return this.jedisCluster.zrangeWithScores(key, start, end);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return this.jedisCluster.zrevrangeWithScores(key, start, end);
    }

    @Override
    public Long zcard(String key) {
        return this.jedisCluster.zcard(key);
    }

    @Override
    public Double zscore(String key, String member) {
        return this.jedisCluster.zscore(key, member);
    }

    @Override
    public List<String> sort(String key) {
        return this.jedisCluster.sort(key);
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return this.jedisCluster.sort(key, sortingParameters);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return this.jedisCluster.zcount(key, min, max);
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return this.jedisCluster.zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return this.jedisCluster.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return this.jedisCluster.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return this.jedisCluster.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return this.jedisCluster.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return this.jedisCluster.zremrangeByRank(key, start, end);
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        return this.jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return this.jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return this.jedisCluster.zlexcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return this.jedisCluster.zrangeByLex(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return this.jedisCluster.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return this.jedisCluster.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return this.jedisCluster.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return this.jedisCluster.zremrangeByLex(key, min, max);
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return this.jedisCluster.linsert(key, where, pivot, value);
    }

    @Override
    public Long lpushx(String key, String... string) {
        return this.jedisCluster.lpushx(key, string);
    }

    @Override
    public Long rpushx(String key, String... string) {
        return this.jedisCluster.rpushx(key, string);
    }

    @Override
    @Deprecated
    public List<String> blpop(String arg) {
        return this.jedisCluster.blpop(arg);
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return this.jedisCluster.blpop(timeout, key);
    }

    @Override
    @Deprecated
    public List<String> brpop(String arg) {
        return this.jedisCluster.blpop(arg);
    }

    @Override
    @Deprecated
    public List<String> brpop(int timeout, String key) {
        return this.jedisCluster.brpop(timeout, key);
    }

    @Override
    public String echo(String string) {
        return this.jedisCluster.echo(string);
    }

    @Override
    @Deprecated
    public Long move(String key, int dbIndex) {
        return this.jedisCluster.move(key, dbIndex);
    }

    @Override
    public Long bitcount(String key) {
        return this.jedisCluster.bitcount(key);
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return this.jedisCluster.bitcount(key, start, end);
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return this.jedisCluster.bitpos(key, value);
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return this.jedisCluster.bitpos(key, value, params);
    }

    @Override
    @Deprecated
    public ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        return this.jedisCluster.hscan(key, cursor);
    }

    @Override
    @Deprecated
    public ScanResult<String> sscan(String key, int cursor) {
        return this.jedisCluster.sscan(key, cursor);
    }

    @Override
    @Deprecated
    public ScanResult<Tuple> zscan(String key, int cursor) {
        return this.jedisCluster.zscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return this.jedisCluster.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return this.jedisCluster.hscan(key, cursor, params);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return this.jedisCluster.sscan(key, cursor);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return this.jedisCluster.sscan(key, cursor, params);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return this.jedisCluster.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return this.jedisCluster.zscan(key, cursor, params);
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return this.jedisCluster.pfadd(key, elements);
    }

    @Override
    public long pfcount(String key) {
        return this.jedisCluster.pfcount(key);
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return this.jedisCluster.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return this.jedisCluster.geodist(key, member1, member2, unit);
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return this.jedisCluster.geohash(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return this.jedisCluster.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return this.jedisCluster.georadius(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return this.jedisCluster.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        return this.jedisCluster.bitfield(key, arguments);
    }

    @Override
    public Long exists(String... keys) {
        return this.jedisCluster.exists(keys);
    }

    @Override
    public Long del(String... keys) {
        return this.jedisCluster.del(keys);
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return this.jedisCluster.blpop(timeout, keys);
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return this.jedisCluster.brpop(timeout, keys);
    }

    @Override
    public List<String> mget(String... keys) {
        return this.jedisCluster.mget(keys);
    }

    @Override
    public String mset(String... keysvalues) {
        return this.jedisCluster.mset(keysvalues);
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return this.jedisCluster.msetnx(keysvalues);
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return this.jedisCluster.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return this.jedisCluster.renamenx(oldkey, newkey);
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return this.jedisCluster.rpoplpush(srckey, dstkey);
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return this.jedisCluster.sdiff(keys);
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return this.jedisCluster.sdiffstore(dstkey, keys);
    }

    @Override
    public Set<String> sinter(String... keys) {
        return this.jedisCluster.sinter(keys);
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return this.jedisCluster.sinterstore(dstkey, keys);
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return this.jedisCluster.smove(srckey, dstkey, member);
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return this.jedisCluster.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(String key, String dstkey) {
        return this.jedisCluster.sort(key, dstkey);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return this.jedisCluster.sunion(keys);
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return this.jedisCluster.sunionstore(dstkey, keys);
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return this.jedisCluster.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return this.jedisCluster.zinterstore(dstkey, params, sets);
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return this.jedisCluster.zunionstore(dstkey, sets);
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return this.jedisCluster.zunionstore(dstkey, params, sets);
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return this.jedisCluster.brpoplpush(source, destination, timeout);
    }

    @Override
    public Long publish(String channel, String message) {
        return this.jedisCluster.publish(channel, message);
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        this.jedisCluster.subscribe(jedisPubSub, channels);
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        this.jedisCluster.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return this.jedisCluster.bitop(op, destKey, srcKeys);
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        return this.jedisCluster.pfmerge(destkey, sourcekeys);
    }

    @Override
    public long pfcount(String... keys) {
        return this.jedisCluster.pfcount(keys);
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        return this.jedisCluster.scan(cursor, params);
    }

    @Override
    public List<String> blpop(String... args) {
        return this.jedisCluster.blpop(1000, args);
    }

    @Override
    public List<String> brpop(String... args) {
        return this.jedisCluster.brpop(1000, args);
    }

    @Override
    @Deprecated
    public String watch(String... keys) {
        logger.warn("集群没有该方法");
        return null;
    }

    @Override
    @Deprecated
    public String unwatch() {
        logger.warn("集群没有该方法");
        return null;
    }

    @Override
    @Deprecated
    public String randomKey() {
        logger.warn("集群没有该方法");
        return null;
    }

    @Override
    @Deprecated
    public ScanResult<String> scan(int cursor) {
        logger.warn("集群没有该方法");
        return null;
    }

    @Override
    @Deprecated
    public ScanResult<String> scan(String cursor) {
        logger.warn("集群没有该方法");
        return null;
    }

    public Jedis getJedis() {
        return null;
    }

    public Object eval(String script, List<String> keys, List<String> args) {
        return this.jedisCluster.eval(script, keys, args);
    }
}
