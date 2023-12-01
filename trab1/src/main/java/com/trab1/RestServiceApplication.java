package com.trab1;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EntityScan(basePackages = { "com.trab1.entity" })
@ComponentScan(basePackages = {"com.trab1"})
@EnableJpaRepositories(basePackages={"com.trab1.repository"})
@EnableTransactionManagement
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
	
	//Java Persistence Architecture (abstração para similares Hibernate)
	//Hibernate (domina)
	@Bean(name = "jpaAdapter")
	public JpaVendorAdapter jpaAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.DERBY);
		return hibernateJpaVendorAdapter;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		SimpleDriverDataSource sds = DataSourceBuilder
		        .create()
		        .type(SimpleDriverDataSource.class)
		        .driverClassName("org.apache.derby.jdbc.EmbeddedDriver")
		        .username(null)
		        .password(null)
		        .url("jdbc:derby:memory:myDB;create=true").build();
		return sds;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Throwable {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource());
		entityManager.setJpaVendorAdapter(jpaAdapter());
		entityManager.setPackagesToScan("com.trab1.entity");
		entityManager.setPersistenceUnitName("defaultPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
