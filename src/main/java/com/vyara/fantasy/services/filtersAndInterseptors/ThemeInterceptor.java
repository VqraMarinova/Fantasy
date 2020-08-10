package com.vyara.fantasy.services.filtersAndInterseptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Component
public class ThemeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        String backgroundImage = "";
        String fontColor = "";
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour > 8 && hour < 20){
            backgroundImage = "/img/day.jpg";
            fontColor = "dayText";
        } else {
            backgroundImage = "/img/night.jpg";

        }
        if(modelAndView!=null) {
            modelAndView.addObject("backgroundImage", backgroundImage);
            modelAndView.addObject("fontColor", fontColor);
        }
    }
}
