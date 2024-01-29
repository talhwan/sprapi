package com.thc.sprapi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        /*
        logger.info("1-1: REQUEST [{}][{}][{}]", requestMethod, requestURI, handler);
        logger.info("1-2: RESPONSE [{}][{}]", response.getStatus(), response.getHeaderNames());
        request.setAttribute("test_auth", "Y");
        response.setHeader("test_header", "!");
        //logger.info("0-1: REQUEST [{}]", request.getHeaderNames());
         */

        /*
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = (String)headerNames.nextElement();
            String value = request.getHeader(name);
            logger.info("0-1: REQUEST [{}][{}]", name, value);
        }
        //String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        //@RequestMapping: HandlerMethod가 넘어온다.
        //정적 리소스: ResourcehttpRequesthandler가 넘어온다.
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
        }

        */

        return true;
    }

    //컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*
        logger.info("post / request [{}]", request.getAttribute("test_auth"));
        logger.info("post // request [{}]", request.getAttribute("test_auth_1"));
        logger.info("2-1: RESPONSE [{}][{}]", response.getStatus(), response.getHeaderNames());
        logger.info("2-2: RESPONSE [{}]", response.getHeader("test_header_1"));
        logger.info("post Handle [{}]", modelAndView);
        */
    }

    //모든것을 마친 후 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*
        logger.info("after / request [{}]", request.getAttribute("test_auth"));
        logger.info("3-1: RESPONSE [{}][{}]", response.getStatus(), response.getHeaderNames());
        if (ex != null) {
            logger.error("afterCompletion error:", ex);
        }
         */
    }
}
