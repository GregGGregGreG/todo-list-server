package edu.greg.spring.todolist.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by greg on 28.06.15.
 */
@Configuration
@Profile("prod")
@EnableTransactionManagement
@EnableJpaRepositories("edu.greg.spring.todolist.todo.persistence.repository")
@PropertySource("classpath:src/profile/dev/dbConfig.properties")
public class ProdDBProfileConfig {

    @Value("#{environment['hibernate.dialect']}")
    private String PROPERTY_NAME_HIBERNATE_DIALECT;
    @Value("#{environment['hibernate.format_sql']}")
    private String PROPERTY_NAME_HIBERNATE_FORMAT_SQL;
    @Value("#{environment['hibernate.hbm2ddl.auto']}")
    private String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO;
    @Value("#{environment['hibernate.ejb.naming_strategy']}")
    private String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY;
    @Value("#{environment['hibernate.show_sql']}")
    private Boolean PROPERTY_NAME_HIBERNATE_SHOW_SQL;

    @Value("#{environment['entitymanager.packages.to.scan']}")
    private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws URISyntaxException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException, URISyntaxException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException, URISyntaxException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(jpaProperty());

        return entityManagerFactoryBean;
    }

    private Properties jpaProperty() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
        jpaProperties.put("hibernate.format_sql", PROPERTY_NAME_HIBERNATE_FORMAT_SQL);
        jpaProperties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
        jpaProperties.put("hibernate.ejb.naming_strategy", PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY);
        jpaProperties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
        return jpaProperties;
    }
}
