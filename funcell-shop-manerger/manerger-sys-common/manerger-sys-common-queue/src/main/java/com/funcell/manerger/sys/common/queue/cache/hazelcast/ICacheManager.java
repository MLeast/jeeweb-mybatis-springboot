package com.funcell.manerger.sys.common.queue.cache.hazelcast;

public interface ICacheManager {
    IHazelcastCacheBean getQueueUpCacheBean();
    IHazelcastCacheBean getOnLineUserCacheBean();
}
