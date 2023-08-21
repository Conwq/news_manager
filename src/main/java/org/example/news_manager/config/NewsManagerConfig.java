package org.example.news_manager.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.news_manager")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class NewsManagerConfig implements WebMvcConfigurer {
	private final Environment env;

	@Autowired
	public NewsManagerConfig(Environment env){
		this.env = env;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getRequiredProperty("mysql.driver_class"));
		dataSource.setJdbcUrl(env.getRequiredProperty("mysql.jdbc_url"));
		dataSource.setUser(env.getRequiredProperty("mysql.user"));
		dataSource.setPassword(env.getRequiredProperty("mysql.password"));
		dataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty("c3p0.connection_pool.min_pool_size")));
		dataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty("c3p0.connection_pool.max_pool_size")));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getRequiredProperty("c3p0.connection_pool.max_idle_time")));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("org.example.news_manager.entity");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws PropertyVetoException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
