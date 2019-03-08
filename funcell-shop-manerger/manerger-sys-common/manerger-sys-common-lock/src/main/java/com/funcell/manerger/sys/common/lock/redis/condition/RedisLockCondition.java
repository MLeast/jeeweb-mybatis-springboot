package com.funcell.manerger.sys.common.lock.redis.condition;


import com.funcell.manerger.sys.common.lock.condition.LockCondition;

public class RedisLockCondition extends LockCondition {
    public RedisLockCondition() {
        super("lock.type", "redisLock");
    }
}