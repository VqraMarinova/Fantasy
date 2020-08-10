package com.vyara.fantasy.services.filtersAndInterseptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Component
public class GreetingInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(session.getAttribute("hourOfDay") != null && session.getAttribute("hourOfDay").toString().equals(Integer.toString(hour))){
            return;
        }

        if(hour < 12){
            session.setAttribute("greeting", "Good Morning, ");
        }

        else if (hour <= 19){
            session.setAttribute("greeting", "Good Afternoon, ");
        }

        else {
            session.setAttribute("greeting", "Good Evening, ");
        }

    }
}
