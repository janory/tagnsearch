package com.tagnsearch.main;

import com.tagnsearch.auth.AuthorizationFilter;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetSocketAddress;

@Configuration
@ComponentScan(basePackages = "com.tagnsearch")
@EnableElasticsearchRepositories(basePackages = "com.tagnsearch.repositories")
@EnableAutoConfiguration
public class StartupSpringBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartupSpringBoot.class, args);
    }

    @Bean
    public FilterRegistrationBean authorizationFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public Client client() {
        TransportClient client = TransportClient.builder().build();
        TransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("localhost", 9300));
        client.addTransportAddress(address);
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}