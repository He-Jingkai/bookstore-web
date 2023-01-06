//package com.hjk.hjkbookstore_backend.config;
//
//import org.neo4j.ogm.session.SessionFactory;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
//import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableNeo4jRepositories(basePackages = "com.hjk.hjkbookstore_backend.repository")
//@EntityScan(basePackages = "com.hjk.hjkbookstore_backend.entity")
//@EnableTransactionManagement
//public class Neo4jConfig {
//    @Bean
//    public SessionFactory sessionFactory() {
//        return new SessionFactory(configuration(),"com.hjk.hjkbookstore_backend.entity");
//    }
//
//    @Bean
//    public org.neo4j.ogm.config.Configuration configuration() {
//        return new org.neo4j.ogm.config.Configuration.Builder()
//                .uri("bolt://localhost:7687").credentials("neo4j", "020101hjk").build();
//    }
//
//    @Bean
//    public Neo4jTransactionManager transactionManager() {
//        return new Neo4jTransactionManager(sessionFactory());
//    }
//}
//
