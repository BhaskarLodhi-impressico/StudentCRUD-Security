package com.springsecurity.spsecurity;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpSecurityApplication.class, args);
	}

}
