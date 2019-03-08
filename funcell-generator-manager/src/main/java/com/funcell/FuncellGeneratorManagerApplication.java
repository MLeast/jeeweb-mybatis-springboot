package com.funcell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.funcell.generator.manager","com.funcell.manerger.sys.common.oss","com.funcell.manerger.sys.ui"
})
@SpringBootApplication
public class FuncellGeneratorManagerApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FuncellGeneratorManagerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FuncellGeneratorManagerApplication.class, args);
	}
}
