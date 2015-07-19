package edu.greg.todolist.config;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by greg on 28.06.15.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("edu.greg.todolist.todo.persistence.repository")
@PropertySource("classpath:persistence-hsql.properties")
public class DataBaseConfig {

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

    @Value("#{environment['db.embedded.name']}")
    private String PROPERTY_NAME_DATABASE;
    @Value("#{environment['db.url']}")
    private String PROPERTY_NAME_DATABASE_URL;
    @Value("#{environment['db.username']}")
    private String PROPERTY_NAME_DATABASE_USERNAME;
    @Value("#{environment['db.password']}")
    private String PROPERTY_NAME_DATABASE_PASSWORD;


//
//    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
//    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
//    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
//    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
//
//    private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
//    private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

    @Bean
    public DataSource dataSource() {
//        BoneCPDataSource dataSource = new BoneCPDataSource();
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
//        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
//        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
//        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
//
//        return dataSource;
        return new EmbeddedDatabaseBuilder()
                .setName(PROPERTY_NAME_DATABASE)
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

//    //default username : sa, password : ''
//    @PostConstruct
//    public void getDbManager() {
//        DatabaseManagerSwing.main(
//                new String[]{
//                        "--url", PROPERTY_NAME_DATABASE_URL,
//                        "--user", PROPERTY_NAME_DATABASE_USERNAME,
//                        "--password", PROPERTY_NAME_DATABASE_PASSWORD});
//    }

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
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

    @Bean
    public ResourceDatabasePopulator initDatabase(DataSource dataSource) throws Exception {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("UTF-8");
//        populator.addScript(new ClassPathResource("sql/H2.sql"));
//        populator.populate(dataSource.getConnection());
        return populator;
    }
}
