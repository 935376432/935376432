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
 * webͨ������
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
        //����@RestController ע�ⷽ����ת��Json ����ʱ��ʹ��@JsonView��ȱʡ����û��ʹ��ע���ʵ������
        // Hibernate4Module ���ڱ����Hibernate δ��ʼ���������Խ��д���ʱ�׳�LazyInit�쳣������
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
        //����@RestController ע�ⷽ����ת��Json ����ʱ��ʹ��@JsonView��ȱʡ����û��ʹ��ע���ʵ������
        // Hibernate4Module ���ڱ����Hibernate δ��ʼ���������Խ��д���ʱ�׳�LazyInit�쳣������
        converters.add(new MappingJackson2HttpMessageConverter(
            Jackson2ObjectMapperBuilder.json().defaultViewInclusion(true)
            .modulesToInstall(new Hibernate4Module()).build()));
        //spring  MVC�ļ�����
        //does/springframework/mvc.html#mvc-config-enable
        converters.add(new ByteArrayHttpMessageConverter());

    }

}
