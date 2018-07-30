package com.scotthensen.portfolio.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(entityManagerFactoryRef = "portfolioEntityManager",
					   transactionManagerRef   = "portfolioTransactionManager",
					   basePackages            = "com.scotthensen.portfolio.persistence.portfolio.repository" )
public class PortfolioDatabaseConfig 
{
	@Autowired
	Environment env;
	
	public PortfolioDatabaseConfig() {
		super();
	}
	
	
	@Bean(name = "portfolioEntityManager")
	public LocalContainerEntityManagerFactoryBean portfolioEntityManager()
	{
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(portfolioDataSource());
		em.setPackagesToScan(new String[] { "com.scotthensen.portfolio.persistence.portfolio.entity"} );
		
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.dialect",      env.getProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.ddl.auto"));
		properties.put("hibernate.format_sql",   env.getProperty("hibernate.sql.format"));
		properties.put("hibernate.show_sql",     env.getProperty("hibernate.sql.show"));
		em.setJpaPropertyMap(properties);
		
		return em;
	}

	@Bean(name = "portfolioDataSource")
	public DataSource portfolioDataSource() 
	{
		final DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(Preconditions.checkNotNull(env.getProperty("portfolio.datasource.driver-class-name")));
		ds.setUrl(            Preconditions.checkNotNull(env.getProperty("portfolio.datasource.url")));
		ds.setUsername(       Preconditions.checkNotNull(env.getProperty("portfolio.datasource.username")));
		ds.setPassword(       Preconditions.checkNotNull(env.getProperty("portfolio.datasource.password")));
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager portfolioTransactionManager()
	{
		final JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(portfolioEntityManager().getObject());
		return tm;
	}
}
