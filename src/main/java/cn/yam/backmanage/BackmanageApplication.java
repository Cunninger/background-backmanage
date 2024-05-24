package cn.yam.backmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.yam.backmanage.mappers")
@SpringBootApplication
public class BackmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackmanageApplication.class, args);
    }

}
