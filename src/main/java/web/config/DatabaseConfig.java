package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class DatabaseConfig {

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan(env.getProperty("db.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(Objects.requireNonNull(getHibernateProperties()));

        return em;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));

        dataSource.setInitialSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("db.initalSize"))));
        dataSource.setMinIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("db.minIdle"))));
        dataSource.setMaxIdle(Integer.parseInt(Objects.requireNonNull(env.getProperty("db.maxIdle"))));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(Objects.requireNonNull(env.getProperty("db.timeBetweenEvictionRunsMillis"))));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(Objects.requireNonNull(env.getProperty("db.minEvictableIdleTimeMillis"))));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("db.testonBorrow")));
        dataSource.setValidationQuery(env.getProperty("db.validationQuery"));

        return dataSource;
    }


    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return manager;
    }

    private Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("hibernate.properties");
            properties.load(is);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
