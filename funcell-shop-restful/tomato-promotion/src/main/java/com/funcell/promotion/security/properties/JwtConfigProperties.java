package com.funcell.promotion.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "funcell.jwt")
@Data
public class JwtConfigProperties {
    private String header = "Authorization";
    private String tokenHead = "Bearer ";
    private String secret = "tomato_promotion";
    private long expiration = 7200L; //过期时间,单位 秒--配合redis设置为2小时
    private String authpath = "auth";
    private String claim_key_username = "username";
    private String claim_key_createdate = "createdate";


}
