package com.yexiao.demo.conf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * @author xuhf
 * @date 2020/12/18 16:15
 **/
@Component
public class RedisSessionDAO extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    private Long expireTime = 1800L;

    private static final String redisShiroPrefix = "redisShiro:";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        saveSession(session);
        return session.getId();    }

    private void saveSession(Session session){
        byte[] serialize = SerializationUtils.serialize(session);
        redisTemplate.execute((RedisCallback<Boolean>) connection->{
            return connection.setEx((redisShiroPrefix + session.getId().toString()).getBytes(),
                    expireTime, serialize );
        });
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        byte[] key = (redisShiroPrefix + serializable.toString()).getBytes();
        byte[] execute = (byte[]) redisTemplate.execute((RedisCallback<byte[]>) connection -> {
            byte[] bytes = connection.get(key);
            return bytes;
        });

        return (Session) SerializationUtils.deserialize(execute);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        redisTemplate.execute((RedisCallback<Void>)connection->{
           connection.del((redisShiroPrefix + session.getId().toString()).getBytes());
           return null;
        });
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set keys = redisTemplate.keys(redisShiroPrefix + "*");
        Set<Session> sessions = new HashSet<>();
        keys.forEach(key->{
            sessions.add((Session) redisTemplate.execute((RedisCallback<Session>)connection->{
                byte[] bytes = connection.get(key.toString().getBytes());
                return (Session) SerializationUtils.deserialize(bytes);
            }));
        });
        return sessions;
    }
}
