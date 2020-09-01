package com.mvcproject.contact.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mvcproject.contact.dao.ContactDao;
import com.mvcproject.contact.dao.ContactDaoImpl;
import com.mvcproject.contact.service.ContactService;
import com.mvcproject.contact.service.ContactServiceImpl;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mvcproject.contact")
public class ContactConfig implements WebMvcConfigurer{

	@Bean
	public DataSource getDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactManagerDb");
		dataSource.setUsername("root");
		dataSource.setPassword("7788");
		return dataSource;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	@Bean
	public ContactService contactService(ContactDao contactDao) {
		return new ContactServiceImpl(contactDao);
	}
	
	@Bean
	public ContactDao contactDao(JdbcTemplate jdbcTemplate) {
		return new ContactDaoImpl(jdbcTemplate);
	}
	
}
