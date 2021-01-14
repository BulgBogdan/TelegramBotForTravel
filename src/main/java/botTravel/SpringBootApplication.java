package botTravel;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.sql.DataSource;
import java.util.Properties;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);

        ApiContextInitializer.init();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi();
            botsApi.registerBot(new CityGuideTravelBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        System.out.println("DataSource: " + dataSource);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("botTravel.entity");

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql",
                environment.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.ddl-auto",
                environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.current_session_context_class",
                environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.put("hibernate.search.default.directory_provider",
                environment.getProperty("spring.jpa.properties.hibernate.search.default.directory_provider"));
        properties.put("hibernate.search.default.indexBase",
                environment.getProperty("spring.jpa.properties.hibernate.search.default.indexBase"));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
