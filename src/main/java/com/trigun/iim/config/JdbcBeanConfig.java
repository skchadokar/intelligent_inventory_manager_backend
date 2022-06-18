package com.trigun.iim.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

//required if want to use basic jdbc with customize query
@Configuration
public class JdbcBeanConfig {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Bean
	public JdbcDao jdbcDao() {
		JdbcDao jdbcDao = new JdbcDao();
		jdbcDao.setDataSource(jdbcTemplate.getDataSource());
		return jdbcDao;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public PhysicalNamingStrategy physicalNamingStrategy() {
		return new PhysicalNamingStrategyStandardImpl();
	}

}