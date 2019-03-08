package com.funcell.manerger.sys.common.idgenerator.redis.config;

import com.funcell.manerger.sys.common.idgenerator.redis.RedisIdGenerator;
import com.funcell.manerger.sys.common.idgenerator.redis.condition.RedisIdGeneratorCondition;
import com.funcell.manerger.sys.common.idgenerator.redis.impl.RedisIdGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisIdGeneratorConfig {
    @Bean
    @Conditional(RedisIdGeneratorCondition.class)
    public RedisIdGenerator redisIdGenerator() {
        return new RedisIdGeneratorImpl();
    }
}
