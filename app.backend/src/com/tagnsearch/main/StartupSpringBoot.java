package com.tagnsearch.main;

import com.tagnsearch.auth.AuthorizationFilter;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

@Configuration
@ComponentScan(basePackages = "com.tagnsearch")
@EnableElasticsearchRepositories(basePackages = "com.tagnsearch.repositories")
@EnableAutoConfiguration
@EnableConfigurationProperties({ ResourceProperties.class })
public class StartupSpringBoot extends WebMvcConfigurerAdapter {

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/index.html")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath,
                                                   Resource location) throws IOException {

                        if ( resourcePath.endsWith(".js")
                                || resourcePath.endsWith(".png")
                                || resourcePath.endsWith(".css")
                                || resourcePath.endsWith(".ico")) {
                            final URL url = location.getURL();
                            return new ClassPathResource("/resources/" + resourcePath, ClassUtils.getDefaultClassLoader());
                        }

                        return location.exists() && location.isReadable() ? location
                                : null;
                    }
                });
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}