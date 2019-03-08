package com.funcell.promotion.moudle.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funcell.promotion.moudle.entity.StatisticsSearchEntity;
import com.funcell.promotion.moudle.mapper.StatisticsSearchMapper;
import com.funcell.promotion.moudle.service.IStatisticsSearchService;
import com.funcell.promotion.utils.PrincipalUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("statisticsSearchService")
@Transactional
public class StatisticsSearchServiceImpl extends ServiceImpl<StatisticsSearchMapper, StatisticsSearchEntity> implements IStatisticsSearchService {


    @Override
    public boolean insertSearchLog(String keyWord) {
        Date date = new Date();
        //fixme 同一用户搜索


        StatisticsSearchEntity statisticsSearchEntity = new StatisticsSearchEntity();
        statisticsSearchEntity.setKeyWord(keyWord);
        statisticsSearchEntity.setSearch_update_time(date);
        statisticsSearchEntity.setUsername(PrincipalUtils.getUsername());

        return insertOrUpdate(statisticsSearchEntity);
        /*try {
            RedisCacheUtils.getRedisCacheManager().set(RedisKeyUtils.getSearchLogKey(System.currentTimeMillis()), statisticsSearchEntity, TomatoConstant.Common.LOG_TIME_REDIS);
            return true;
        } catch (Exception e) {
            return false;
        }*/
    }


}
