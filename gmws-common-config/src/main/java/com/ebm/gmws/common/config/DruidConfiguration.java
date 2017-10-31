package com.ebm.gmws.common.config;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.ebm.gmws.common.config.pojo.DBConstant;

@Configuration
@EnableConfigurationProperties(DBConstant.class)
public class DruidConfiguration {

	@Resource
	private DBConstant dbInfo;
	
	@ConditionalOnClass(DruidDataSource.class)
	@ConditionalOnProperty(name="spring.datasource.type", havingValue="com.alibaba.druid.pool.DruidDataSource", matchIfMissing=true)
	class Druid extends DruidConfiguration {
		
		@Bean(name = "dataSource")
		public DruidDataSource dataSource(DataSourceProperties properties) throws SQLException {
			properties.setUsername(dbInfo.getUsername());
			properties.setPassword(dbInfo.getPassword());
			properties.setUrl(dbInfo.getUrl());
			
			DruidDataSource druidDataSource = (DruidDataSource) properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
			druidDataSource.setFilters("config");
			//druidDataSource.addConnectionProperty("config.decrypt", "true");
			//druidDataSource.addConnectionProperty("config.decrypt.key", publicKey);
			druidDataSource.addFilters("com.alibaba.druid.filter.stat.StatFilter");
			DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
			String validationQuery = databaseDriver.getValidationQuery();
			if(validationQuery != null) {
				druidDataSource.setValidationQuery(validationQuery);
			}
			System.out.println("DruidConfiguration init completed................");
			return druidDataSource;
		}

		/*@Bean(name = "sqlSessionFactory")
		@Primary
		public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(dataSource);
			bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml"));
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));

			return bean.getObject();
		}

		@Bean(name = "sqlSessionTemplate")
		@Primary
		public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
			return new SqlSessionTemplate(sqlSessionFactory);
		}*/
	}
}
