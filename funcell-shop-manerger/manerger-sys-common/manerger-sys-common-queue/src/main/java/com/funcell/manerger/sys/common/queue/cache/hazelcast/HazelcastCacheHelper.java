package com.funcell.manerger.sys.common.queue.cache.hazelcast;

import com.funcell.manerger.sys.common.queue.bean.ClusterBean;
import com.funcell.manerger.sys.common.queue.cache.hazelcast.enums.CacheEnum;
import com.funcell.manerger.sys.common.queue.cache.hazelcast.impl.BusinessCacheImpl;
import com.funcell.manerger.sys.common.queue.cache.hazelcast.impl.QueueUpCacheImpl;
import com.funcell.manerger.sys.common.queue.cache.hazelcast.impl.OnlineUsersCacheImpl;
import com.funcell.manerger.sys.common.utils.SpringContextHolder;

public class HazelcastCacheHelper implements ICacheManager {
    private static HazelcastCacheHelper mInstance = null;

    public static HazelcastCacheHelper getInstance(){
        if(mInstance == null){
            synchronized (ClusterBean.class){
                if(mInstance == null){
                    mInstance = new HazelcastCacheHelper();
                }
            }
        }
        return mInstance;
    }

    @Override
    public IHazelcastCacheBean getQueueUpCacheBean() {
        return SpringContextHolder.getBean(QueueUpCacheImpl.class).getInstance(CacheEnum.CACHE_HAZELCAST_QUEUEUP.toString());
    }

    @Override
    public IHazelcastCacheBean getOnLineUserCacheBean() {
        return SpringContextHolder.getBean(OnlineUsersCacheImpl.class).getInstance(CacheEnum.CACHE_HAZELCAST_ONLINE_USER.toString());
    }

    public IHazelcastCacheBean getBusinessCacheBean(){
        return SpringContextHolder.getBean(BusinessCacheImpl.class).getInstance(CacheEnum.CACHE_HAZELCAST_BUSINESS.toString());
    }
}
