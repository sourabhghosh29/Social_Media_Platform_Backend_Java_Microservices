package com.fun.club.auth.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "barEntityManagerFactory", transactionManagerRef = "barTransactionManager", basePackages = {
      "com.fun.club.domain.repository" })
public class FunClubDbConfig {

  @Bean(name = "funClubDataSource")
  @ConfigurationProperties(prefix = "spring.fun.club.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "barEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("funClubDataSource") DataSource dataSource) {
    return builder
          .dataSource(dataSource)
          .packages("com.fun.club.domain.entity")
          //          .persistenceUnit("fun-club")
          .build();
  }

  @Bean(name = "barTransactionManager")
  public PlatformTransactionManager barTransactionManager(
        @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
    return new JpaTransactionManager(barEntityManagerFactory);
  }
}