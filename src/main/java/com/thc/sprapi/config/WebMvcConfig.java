package com.thc.sprapi.config;

import com.thc.sprapi.interceptor.DefaultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //파일 접근하는 통로 열어두고 싶다면 사용하면 됨
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        // 절대경로 file:///c:/upload/
        // 상대경로 file:./upload/
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:" + "C:/workspace/uploadfiles/")
                .setCachePeriod(60 * 60) // 초 단위 => 한시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    //인터셉터 설정을 위함
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor())
                .addPathPatterns("/api/**") //인터셉터가 실행되야 하는 url 패턴 설정
                .excludePathPatterns("/resources/**"); //인터셉터가 실행되지 않아야 하는 url 패턴
    }

}