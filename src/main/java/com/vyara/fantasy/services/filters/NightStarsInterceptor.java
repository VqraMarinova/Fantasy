package com.vyara.fantasy.services.filters;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class NightStarsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = "";
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour = localDateTime.getHour();

        if (hour >= 21 || hour <= 6) {
            url = "/img/winter.png";
        }

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                modelAndView
                        .addObject("url", url);
            }
        }
    }
}
