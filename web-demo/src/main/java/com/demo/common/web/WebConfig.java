/*
 *jiji java
 */
package com.demo.common.web;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
/**
 * web通用配置
 */
@Configuration
@EnableWebMvc
@ControllerAdvice
@EnableSpringDataWebSupport
public class WebConfig extends SpringDataWebConfiguration {

    /*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new BeanNameViewResolver());

        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
        //处理@RestController 注解方法中转换Json 对象时，使用@JsonView，缺省包含没有使用注解的实体属性
        // Hibernate4Module 用于避免对Hibernate 未初始化集合属性进行处理时抛出LazyInit异常的问题
        registry.enableContentNegotiation(new MappingJackson2JsonView(
            Jackson2ObjectMapperBuilder.json().defaultViewInclusion(true)
            .modulesToInstall(new Hibernate4Module()).build()));

    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //处理@RestController 注解方法中转换Json 对象时，使用@JsonView，缺省包含没有使用注解的实体属性
        // Hibernate4Module 用于避免对Hibernate 未初始化集合属性进行处理时抛出LazyInit异常的问题
        converters.add(new MappingJackson2HttpMessageConverter(
            Jackson2ObjectMapperBuilder.json().defaultViewInclusion(true)
            .modulesToInstall(new Hibernate4Module()).build()));
        //spring  MVC文件下载
        //does/springframework/mvc.html#mvc-config-enable
        converters.add(new ByteArrayHttpMessageConverter());

    }

}
