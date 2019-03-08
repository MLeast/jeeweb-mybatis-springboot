package com.funcell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.funcell.shop.sys.service","com.funcell.manerger.sys.common.limit"
})
@SpringBootApplication
public class ShopSysServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ShopSysServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopSysServiceApplication.class, args);
	}
}
