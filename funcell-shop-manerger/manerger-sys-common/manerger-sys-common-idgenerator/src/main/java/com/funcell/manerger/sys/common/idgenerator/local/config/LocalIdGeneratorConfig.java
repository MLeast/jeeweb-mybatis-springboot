package com.funcell.manerger.sys.common.idgenerator.local.config;

import com.funcell.manerger.sys.common.idgenerator.local.CustomLocalIdGenerator;
import com.funcell.manerger.sys.common.idgenerator.local.LocalIdGenerator;
import com.funcell.manerger.sys.common.idgenerator.local.condition.CustomLocalIdGeneratorCondition;
import com.funcell.manerger.sys.common.idgenerator.local.condition.LocalIdGeneratorCondition;
import com.funcell.manerger.sys.common.idgenerator.local.impl.CustomLocalIdGeneratorImpl;
import com.funcell.manerger.sys.common.idgenerator.local.impl.DefaultLocalIdGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalIdGeneratorConfig {
    @Bean(name = "defaultLocalIdGenerator")
    @Conditional(LocalIdGeneratorCondition.class)
    public LocalIdGenerator defaultLocalIdGenerator(){
        return new DefaultLocalIdGeneratorImpl();
    }

    @Bean(name = "customLocalIdGenerator")
    @Conditional(CustomLocalIdGeneratorCondition.class)
    public CustomLocalIdGenerator customLocalIdGenerator(){
        return new CustomLocalIdGeneratorImpl();
    }
}
