package com.neeraj.poc.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableCassandraRepositories(basePackages = "com.neeraj.poc.kafka.repository.cassandra")
@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "cassandraEntityManagerFactory",
//        basePackages = { "com.neeraj.poc.kafka.repository.cassandra" }
//)
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.cassandra.keyspace-name}")
    private String keySpaceName;

    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.password}")
    private String password;

    @Value("${spring.cassandra.port}")
    private int port;

    @Value("${spring.cassandra.schema-action}")
    private String schemaAction;

    @Value("${spring.cassandra.username}")
    private String username;

    @Value("${spring.cassandra.local-datacenter}")
    private String datacenter;

    @Override
    protected String getKeyspaceName() {
        return keySpaceName;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(schemaAction.toUpperCase());
    }

    @Override
    protected String getLocalDataCenter() {
        return datacenter;
    }

    @Override
    public CassandraMappingContext cassandraMapping() {
        return new CassandraMappingContext();
    }

    @Bean
    public CassandraConverter cassandraConverter() {
        return new MappingCassandraConverter(cassandraMapping());
    }

    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean session = super.cassandraSession();
        // Set the username and password for authentication
        session.setUsername(username);
        session.setPassword(password);

        // Set the contact points and port
        session.setContactPoints(getContactPoints());
        session.setPort(getPort());

        // Set the local data center
        session.setLocalDatacenter(datacenter);

        // Set the schema action based on the application properties
        session.setSchemaAction(getSchemaAction());

        // Set the manual converter
        session.setConverter(cassandraConverter());

        return session;
    }

}
