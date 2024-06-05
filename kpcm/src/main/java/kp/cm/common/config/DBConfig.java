package kp.cm.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,  ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(applicationContext.getResources("classpath:mybatis/kpcm/**/*.xml"));
        bean.setTypeAliasesPackage("kp.cm.**.vo");
        bean.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/mybatis-config.xml")); // 카멜케이스 적용 위함
        return bean.getObject();

    }
}
