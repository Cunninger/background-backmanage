package cn.yam.backmanage.config;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 功能：
 * 日期：2024/7/3 上午11:47
 */

@Configuration// 配置类
@EnableTransactionManagement//  开启事务
@MapperScan("cn.yam.backmanage.mappers")// 扫描mapper接口
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}